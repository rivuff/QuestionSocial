package com.quoraApp.quroa.service;

import com.quoraApp.quroa.Enums.SchemaType;
import com.quoraApp.quroa.dto.LikeRequestDto;
import com.quoraApp.quroa.dto.LikeResponseDto;

import reactor.core.publisher.Mono;

public interface ILikeService {
    
    public Mono<LikeResponseDto> createLike(LikeRequestDto likeRequestDto);

    public Mono<LikeResponseDto> countLikeByTargetIdAndTargetType(String targetId, SchemaType targetType);

    public Mono<LikeResponseDto> countDisLikeByTargetIdAndTargetType(String targetId, SchemaType targetType);

    public Mono<LikeResponseDto> toggleLike(String targetId, SchemaType targetType, boolean isLike);
}
