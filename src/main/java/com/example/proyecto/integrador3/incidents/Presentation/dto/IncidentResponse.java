package com.example.proyecto.integrador3.incidents.Presentation.dto;

import com.example.proyecto.integrador3.incidents.enums.IncidentCategory;
import com.example.proyecto.integrador3.incidents.enums.IncidentPriority;
import com.example.proyecto.integrador3.incidents.enums.IncidentState;
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
    private LocalDate resolutionDate;
    private LocalDate updatedAt;
    private IncidentCategory category;
    private IncidentState state;
    private String address;
    private IncidentPriority priority;
}