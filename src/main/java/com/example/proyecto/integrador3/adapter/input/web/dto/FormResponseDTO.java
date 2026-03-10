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
public class FormResponseDTO {
    @NotBlank
    private String type;
    @NotBlank
    String priority;
    @NotBlank
    private String description;
}
