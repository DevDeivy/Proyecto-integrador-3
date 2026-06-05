package com.example.proyecto.integrador3.incidents.Presentation.controller;

import com.example.proyecto.integrador3.adapter.output.persistence.entity.User;
import com.example.proyecto.integrador3.incidents.Presentation.dto.IncidentRequest;
import com.example.proyecto.integrador3.incidents.Presentation.dto.IncidentResponse;
import com.example.proyecto.integrador3.incidents.application.service.IncidentService;
import com.example.proyecto.integrador3.incidents.domain.model.Incident;
import com.example.proyecto.integrador3.incidents.enums.IncidentState;
import jakarta.validation.Valid;
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
            @Valid @RequestBody IncidentRequest request,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();

        Incident incident = Incident.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .address(request.getAddress())
                .priority(request.getPriority())
                .userId(user.getId())
                .build();

        Incident saved = incidentService.createIncident(incident);
        return ResponseEntity.ok(mapToResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<IncidentResponse>> getAll() {
        List<IncidentResponse> response = incidentService.getAllIncidents()
                .stream()
                .map(this::mapToResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponse> getById(@PathVariable Long id) {
        return incidentService.getIncidentById(id)
                .map(i -> ResponseEntity.ok(mapToResponse(i)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/state")
    public ResponseEntity<IncidentResponse> updateState(
            @PathVariable Long id,
            @RequestParam IncidentState state
    ) {
        Incident updated = incidentService.updateState(id, state);
        return ResponseEntity.ok(mapToResponse(updated));
    }

    private IncidentResponse mapToResponse(Incident i) {
        return IncidentResponse.builder()
                .id(i.getId())
                .title(i.getTitle())
                .description(i.getDescription())
                .date(i.getDate())
                .deadline(i.getDeadline())
                .resolutionDate(i.getResolutionDate())
                .updatedAt(i.getUpdatedAt())
                .category(i.getCategory())
                .state(i.getState())
                .address(i.getAddress())
                .priority(i.getPriority())
                .build();
    }
}