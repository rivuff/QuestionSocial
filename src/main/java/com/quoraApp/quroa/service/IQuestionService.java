package com.quoraApp.quroa.service;

import com.quoraApp.quroa.dto.CreateQuestionRqstDto;
import com.quoraApp.quroa.dto.QuestionResponseDto;
import com.quoraApp.quroa.model.Question;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    Mono<QuestionResponseDto> createQuestion(CreateQuestionRqstDto question);

    Flux<Question> getAllQuestionsByAuthorId(String authorId);
} 