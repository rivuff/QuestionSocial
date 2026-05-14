package com.quoraApp.quroa.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.quoraApp.quroa.Enums.SchemaType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "likes")
public class Like {
    
    @Id
    private String id;

    private String targetId;

    private SchemaType targetType;

    @CreatedDate
    private LocalDateTime createdAt;

    private boolean isLike;
}
