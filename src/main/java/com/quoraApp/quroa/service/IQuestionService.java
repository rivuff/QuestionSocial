package com.quoraApp.quroa.service;

import com.quoraApp.quroa.dto.CreateQuestionRqstDto;
import com.quoraApp.quroa.dto.QuestionResponseDto;
import com.quoraApp.quroa.model.Question;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    Mono<QuestionResponseDto> createQuestion(CreateQuestionRqstDto question);

    Flux<Question> getAllQuestionsByAuthorId(String authorId);

    Flux<QuestionResponseDto> searchQuestionOnQuery(String query, int offset, int page);

    Flux<QuestionResponseDto> getAllQuestionWithCursor(String cursor, int size);

    Mono<QuestionResponseDto> getQuestionById(String id);

} 