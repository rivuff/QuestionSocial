package com.quoraApp.quroa.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.quoraApp.quroa.model.Answer;

@Repository
public interface AnswerRepository extends ReactiveMongoRepository<Answer, String> {
    
}
