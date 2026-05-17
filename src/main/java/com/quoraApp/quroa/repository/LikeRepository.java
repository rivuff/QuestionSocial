package com.quoraApp.quroa.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.quoraApp.quroa.model.Like;

public interface LikeRepository extends ReactiveMongoRepository<Like, String> {

}
