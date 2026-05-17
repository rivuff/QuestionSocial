package com.quoraApp.quroa.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quoraApp.quroa.repository.QuestionRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class TopViewedQuestionService implements ITopViewedQuestionService{
    
    private final QuestionRepository questionRepository;
    private final ConcurrentHashMap<String, Integer> viewCounts = new ConcurrentHashMap<>();


    @PostConstruct
    public void init(){
        questionRepository.findAll()
                        .filter(qustion -> qustion.getViewCount()!=null)
                        .subscribe(q -> viewCounts.put(q.getId(), q.getViewCount()));

    }

    public void updateTopViewed(String questionId){
        viewCounts.merge(questionId, 1, (a, b) -> a + b);
    }

    @Override
    public Flux<Map.Entry<String, Integer>> getTop3(){
        return Flux.fromIterable(viewCounts.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(3)
            .collect(Collectors.toList()));
    }

}
