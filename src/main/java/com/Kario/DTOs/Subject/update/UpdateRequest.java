package com.Kario.DTOs.Subject.update;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateRequest {

    private String name;
    private String color;
    private Boolean active;
}
