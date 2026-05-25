package com.example.proyecto.integrador3.incidents.domain.ports;

import com.example.proyecto.integrador3.incidents.domain.model.Incident;
import java.util.List;

public interface IncidentRepositoryPort {

    Incident save(Incident incident);
    List<Incident> findAll();
}