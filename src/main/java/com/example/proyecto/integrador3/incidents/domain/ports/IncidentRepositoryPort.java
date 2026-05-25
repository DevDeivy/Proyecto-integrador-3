package com.example.proyecto.integrador3.incidents.domain.ports;

import com.example.proyecto.integrador3.incidents.domain.model.Incident;

public interface IncidentRepositoryPort {

    Incident save(Incident incident);
}