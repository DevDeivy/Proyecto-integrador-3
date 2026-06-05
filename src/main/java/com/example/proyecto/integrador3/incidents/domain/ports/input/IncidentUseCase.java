package com.example.proyecto.integrador3.incidents.domain.ports.input;

import com.example.proyecto.integrador3.incidents.domain.model.Incident;
import com.example.proyecto.integrador3.incidents.enums.IncidentState;

import java.util.List;
import java.util.Optional;

public interface IncidentUseCase {
    Incident createIncident(Incident incident);
    List<Incident> getAllIncidents();
    Optional<Incident> getIncidentById(Long id);
    Incident updateState(Long id, IncidentState newState);
}