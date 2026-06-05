package com.example.proyecto.integrador3.incidents.domain.ports;

import com.example.proyecto.integrador3.incidents.domain.model.Incident;
import com.example.proyecto.integrador3.incidents.enums.IncidentState;

import java.util.List;
import java.util.Optional;

public interface IncidentRepositoryPort {
    Incident save(Incident incident);
    List<Incident> findAll();
    Optional<Incident> findById(Long id);
    Incident updateState(Long id, IncidentState newState);
}