package com.example.proyecto.integrador3.incidents.domain.model;

import com.example.proyecto.integrador3.incidents.enums.IncidentCategory;
import com.example.proyecto.integrador3.incidents.enums.IncidentPriority;
import com.example.proyecto.integrador3.incidents.enums.IncidentState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Incident {
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private LocalDate deadline;
    private LocalDate resolutionDate;
    private LocalDate updatedAt;
    private Long userId;
    private IncidentCategory category;
    private IncidentState state;
    private String address;
    private IncidentPriority priority;
}