package com.quoraApp.quroa.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.quoraApp.quroa.config.KafkaConfig;
import com.quoraApp.quroa.event.ViewCountEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class KafkaEventProducer {

    private final KafkaTemplate<String, ViewCountEvent> kafkaTemplate;

    public void publishViewCountEvent(ViewCountEvent viewCountEvent) {
        kafkaTemplate.send(KafkaConfig.TOPIC_NAME, viewCountEvent.getTargetId(), viewCountEvent)
            .whenComplete((result, err) -> {
                if (err != null) {
                    System.err.println("Error publishing view count event: " + err);
                } else {
                    System.out.println("Successfully published view count event");
                }
            });
    }
}