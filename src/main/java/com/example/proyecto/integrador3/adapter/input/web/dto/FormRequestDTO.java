package com.example.proyecto.integrador3.adapter.input.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormRequestDTO {
    @NotBlank
    private String typeRequest;
    private String section;
    @NotBlank
    private String description;
    private String priority;
    private String category;
}
