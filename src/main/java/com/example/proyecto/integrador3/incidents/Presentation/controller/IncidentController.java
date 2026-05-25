package com.example.proyecto.integrador3.incidents.Presentation.controller;

import com.example.proyecto.integrador3.adapter.output.persistence.entity.User;
import com.example.proyecto.integrador3.incidents.Presentation.dto.IncidentRequest;
import com.example.proyecto.integrador3.incidents.Presentation.dto.IncidentResponse;
import com.example.proyecto.integrador3.incidents.application.service.IncidentService;
import com.example.proyecto.integrador3.incidents.domain.model.Incident;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @PostMapping
    public ResponseEntity<IncidentResponse> create(
            @RequestBody IncidentRequest request,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();

        Incident incident = Incident.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .categoryId(request.getCategoryId())
                .stateId(request.getStateId())
                .addressId(request.getAddressId())
                .priorityId(request.getPriorityId())
                .userId(user.getId())
                .build();

        Incident saved = incidentService.createIncident(incident);

        return ResponseEntity.ok(
                IncidentResponse.builder()
                        .id(saved.getId())
                        .title(saved.getTitle())
                        .description(saved.getDescription())
                        .date(saved.getDate())
                        .deadline(saved.getDeadline())
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<List<IncidentResponse>> getAll() {
        List<IncidentResponse> response = incidentService.getAllIncidents()
                .stream()
                .map(i -> IncidentResponse.builder()
                        .id(i.getId())
                        .title(i.getTitle())
                        .description(i.getDescription())
                        .date(i.getDate())
                        .deadline(i.getDeadline())
                        .build())
                .toList();

        return ResponseEntity.ok(response);
    }
}