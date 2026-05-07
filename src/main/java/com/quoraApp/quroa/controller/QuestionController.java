package com.quoraApp.quroa.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quoraApp.quroa.dto.CreateQuestionRqstDto;
import com.quoraApp.quroa.dto.QuestionResponseDto;
import com.quoraApp.quroa.service.QuestionService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping()
    public Mono<QuestionResponseDto> createQuestion(@RequestBody CreateQuestionRqstDto questionRqstDto){

        return questionService.createQuestion(questionRqstDto)
                    .doOnSuccess(response -> System.out.println("Question created successfully" + response))
                    .doOnError(error -> System.out.println("question not creater due to"+ error));
    }
    
}
