package com.quoraApp.quroa.service;

import org.springframework.stereotype.Service;

import com.quoraApp.quroa.adapter.QuestionResponseAdapter;
import com.quoraApp.quroa.dto.CreateQuestionRqstDto;
import com.quoraApp.quroa.dto.QuestionResponseDto;
import com.quoraApp.quroa.model.Question;
import com.quoraApp.quroa.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class QuestionService implements IQuestionService {
    
    private final QuestionRepository questionRepository;


    @Override
    public Mono<QuestionResponseDto> createQuestion(CreateQuestionRqstDto questionDTO) {
        Question question = Question.builder().
                                title(questionDTO.getTitle())
                                .description(questionDTO.getDescription())
                                .authorId(questionDTO.getAuthorId())
                                .build();

        return questionRepository.save(question)
                .map(QuestionResponseAdapter::toQuestionResponseDto)
                .doOnSuccess(response -> System.out.println("Question rcreated sucessfully" + response))
                .doOnError(error -> System.out.println("Error found" + error));
    }

    @Override
    public Flux<Question> getAllQuestionsByAuthorId(String authorId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllQuestionsByAuthorId'");
    }
}
