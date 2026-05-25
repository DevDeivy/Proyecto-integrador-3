package com.example.proyecto.integrador3.incidents.domain.ports.input;

import com.example.proyecto.integrador3.incidents.domain.model.Incident;
import java.util.List;

public interface IncidentUseCase {

    Incident createIncident(Incident incident);
    List<Incident> getAllIncidents();
}