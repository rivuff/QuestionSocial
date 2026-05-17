package com.quoraApp.quroa.service;

import java.util.Map;

import com.quoraApp.quroa.model.Question;

import reactor.core.publisher.Flux;

public interface ITopViewedQuestionService {
    public Flux<Map.Entry<String, Integer>> getTop3();
}
