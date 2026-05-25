package com.example.proyecto.integrador3.incidents.application.service;

import com.example.proyecto.integrador3.incidents.domain.model.Incident;
import com.example.proyecto.integrador3.incidents.domain.ports.IncidentRepositoryPort;
import com.example.proyecto.integrador3.incidents.domain.ports.input.IncidentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class IncidentService implements IncidentUseCase {

    private final IncidentRepositoryPort incidentRepositoryPort;

    @Override
    public Incident createIncident(Incident incident) {

        return incidentRepositoryPort.save(incident);
    }
}