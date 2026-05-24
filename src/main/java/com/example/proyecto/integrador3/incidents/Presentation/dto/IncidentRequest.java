package com.example.proyecto.integrador3.incidents.Presentation.dto;

import lombok.Data;

@Data
public class IncidentRequest {

    private String title;

    private String description;

    private String categoryId;

    private String stateId;

    private String addressId;

    private String priorityId;
}