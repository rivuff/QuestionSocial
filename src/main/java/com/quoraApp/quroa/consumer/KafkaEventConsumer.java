package com.quoraApp.quroa.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.quoraApp.quroa.config.KafkaConfig;
import com.quoraApp.quroa.event.ViewCountEvent;
import com.quoraApp.quroa.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaEventConsumer {

    private final QuestionRepository questionRepository;

    @KafkaListener(
        topics = KafkaConfig.TOPIC_NAME,
        groupId = "${spring.kafka.consumer.group-id:view-count-consumer}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleViewCountEvent(ViewCountEvent viewCountEvent) {
        System.out.println("MESSAGE RECEIVED = " + viewCountEvent);

        questionRepository.findById(viewCountEvent.getTargetId())
            .flatMap(question -> {
                Integer views = question.getViewCount();
                if (views == null) views = 0;
                question.setViewCount(views + 1);
                return questionRepository.save(question);
            })
            .doOnSuccess(q -> System.out.println("QUESTION UPDATED: " + q))
            .doOnError(e -> System.err.println("ERROR UPDATING QUESTION: " + e.getMessage()))
            .subscribe();
    }
}