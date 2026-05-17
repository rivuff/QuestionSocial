package com.quoraApp.quroa.controller;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quoraApp.quroa.Enums.SchemaType;
import com.quoraApp.quroa.dto.CreateQuestionRqstDto;
import com.quoraApp.quroa.dto.QuestionResponseDto;
import com.quoraApp.quroa.event.ViewCountEvent;
import com.quoraApp.quroa.producer.KafkaEventProducer;
import com.quoraApp.quroa.service.QuestionService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final KafkaEventProducer kafkaEventProducer;

    @PostMapping()
    public Mono<QuestionResponseDto> createQuestion(@RequestBody CreateQuestionRqstDto questionRqstDto){

        return questionService.createQuestion(questionRqstDto)
                    .doOnSuccess(response -> System.out.println("Question created successfully" + response))
                    .doOnError(error -> System.out.println("question not creater due to"+ error));
    }

    @GetMapping("/search")
    public Flux<QuestionResponseDto> searchQuestionOnQuery(
        @RequestParam String query,
        @RequestParam int offset,
        @RequestParam int page
    ){
        return questionService.searchQuestionOnQuery(query, offset, page);
    }

    @GetMapping("/search/cursor")
    public Flux<QuestionResponseDto> getAllQuestion(
        @RequestParam(required = false) String cursor,
        @RequestParam(defaultValue = "10") int size
    ){
        return questionService.getAllQuestionWithCursor(cursor, size)
                .doOnComplete(()-> System.out.println("fetched all questions"))
                .doOnError((error)-> System.out.println("Could not fetch details "+ error ));
    }

    @GetMapping("/question")
    public Mono<QuestionResponseDto> getQuestionById(
        @RequestParam String id
    ){
        return questionService.getQuestionById(id)
                .doOnSuccess((suc)-> {
                    System.out.println("successfully fetched product");
                    ViewCountEvent viewCountEven = ViewCountEvent.builder()
                                                    .targetId(id)
                                                    .targetType(SchemaType.QUESTION)
                                                    .timeStamp(LocalDateTime.now())
                                                    .build();

                    kafkaEventProducer.publishViewCountEvent(viewCountEven);
                    
                })
                .doOnError((err)-> System.out.println("Error occured"));
    }
    
}
