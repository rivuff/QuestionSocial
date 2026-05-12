package com.quoraApp.quroa.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.quoraApp.quroa.model.Question;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuestionRepository extends ReactiveMongoRepository<Question, String>{

    Mono<Question> findByTitle(String title);

    Flux<Question> findAllQuestionByAuthorId(String authorId);


    @Query("{ '$or': [{'title': { $regex: ?0, $options: 'i'}}, { 'content': { $regex: ?0, $options: i} }] }")
    Flux<Question> searchQuestionOnQuery(String query, Pageable pageable);

    Flux<Question> findCreatedAtGreaterThanOrderByCreatedAt(LocalDateTime cursor, Pageable pageable);

    Flux<Question> findTop10OrderByCreatedAtAsc();  
}