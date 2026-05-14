package com.quoraApp.quroa.dto;

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
public class LikeRequestDto {
    @NotBlank(message = "target Id should not be blank")
    private String targetId;

    @NotBlank(message = "target Id should not be blank")
    private SchemaType targetType;

    @NotBlank(message = "target Id should not be blank")
    private boolean isLike;
}
