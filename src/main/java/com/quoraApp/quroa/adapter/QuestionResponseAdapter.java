package com.quoraApp.quroa.adapter;

import com.quoraApp.quroa.dto.QuestionResponseDto;
import com.quoraApp.quroa.model.Question;

public class QuestionResponseAdapter {
    
    public static QuestionResponseDto toQuestionResponseDto(Question question){
        return QuestionResponseDto.builder()
                                .title(question.getTitle())
                                .id(question.getId())
                                .description(question.getDescription())
                                .authorId(question.getAuthorId())
                                .createdAt(question.getCreatedAt())
                                .build();
    }

}