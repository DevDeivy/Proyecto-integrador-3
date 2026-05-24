package com.example.proyecto.integrador3.incidents.domain.ports.input;

import com.example.proyecto.integrador3.incidents.domain.model.Incident;

public interface IncidentUseCase {

    Incident createIncident(Incident incident);
}