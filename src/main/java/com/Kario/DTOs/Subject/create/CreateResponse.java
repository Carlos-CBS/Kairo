package com.Kario.DTOs.Subject.create;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateResponse {
    
    private Integer id;
    private String name;
    private String color;
    private LocalDateTime createAt;
}
