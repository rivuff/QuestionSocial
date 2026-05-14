package com.quoraApp.quroa.service;

import org.springframework.stereotype.Service;

import com.quoraApp.quroa.dto.AnswerRequestDto;
import com.quoraApp.quroa.dto.AnswerResponseDto;

import reactor.core.publisher.Mono;


public interface IAnswerService {
    public Mono<AnswerResponseDto> createAnswer(AnswerRequestDto answerRequestDto);

    public Mono<AnswerResponseDto> getAnswerById(AnswerRequestDto answerRequestDto);
}
