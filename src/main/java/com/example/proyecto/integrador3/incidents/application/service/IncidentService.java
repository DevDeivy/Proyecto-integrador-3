package com.example.proyecto.integrador3.incidents.application.service;

import com.example.proyecto.integrador3.incidents.domain.model.Incident;
import com.example.proyecto.integrador3.incidents.domain.ports.IncidentRepositoryPort;
import com.example.proyecto.integrador3.incidents.domain.ports.input.IncidentUseCase;
import com.example.proyecto.integrador3.incidents.enums.IncidentState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidentService implements IncidentUseCase {

    private final IncidentRepositoryPort incidentRepositoryPort;

    @Override
    public Incident createIncident(Incident incident) {
        return incidentRepositoryPort.save(incident);
    }

    @Override
    public List<Incident> getAllIncidents() {
        return incidentRepositoryPort.findAll();
    }

    @Override
    public Optional<Incident> getIncidentById(Long id) {
        return incidentRepositoryPort.findById(id);
    }

    @Override
    public Incident updateState(Long id, IncidentState newState) {
        return incidentRepositoryPort.updateState(id, newState);
    }
}