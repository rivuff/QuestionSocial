package com.quoraApp.quroa.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.quoraApp.quroa.Enums.SchemaType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeResponseDto {
    private String id;

    private String targetId;

    private SchemaType targetType;

    private boolean isLike;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
