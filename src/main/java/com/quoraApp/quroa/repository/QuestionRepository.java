package com.quoraApp.quroa.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.quoraApp.quroa.model.Question;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuestionRepository extends ReactiveMongoRepository<Question, String>{

    Mono<Question> findByTitle(String title);

    Flux<Question> findAllQuestionByAuthorId(String authorId);

}