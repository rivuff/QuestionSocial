package com.quoraApp.quroa.event;

import java.time.Instant;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quoraApp.quroa.Enums.SchemaType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewCountEvent {
    
    private String targetId;
    
    private SchemaType targetType;

    private LocalDateTime timeStamp;
    
}
 