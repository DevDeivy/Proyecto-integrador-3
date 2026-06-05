package com.example.proyecto.integrador3.incidents.Presentation.dto;

import com.example.proyecto.integrador3.incidents.enums.IncidentCategory;
import com.example.proyecto.integrador3.incidents.enums.IncidentPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IncidentRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private IncidentCategory category;

    @NotBlank
    private String address;

    @NotNull
    private IncidentPriority priority;
}