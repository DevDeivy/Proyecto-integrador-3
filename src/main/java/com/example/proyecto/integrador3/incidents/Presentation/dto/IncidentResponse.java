package com.example.proyecto.integrador3.incidents.Presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class IncidentResponse {

    private Long id;

    private String title;

    private String description;

    private LocalDate date;

    private LocalDate deadline;
}