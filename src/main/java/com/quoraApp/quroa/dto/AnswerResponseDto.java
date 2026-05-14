package com.quoraApp.quroa.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResponseDto {
    private String id;

    private String content;

    private String targetId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
