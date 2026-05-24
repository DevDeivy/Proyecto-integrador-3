package com.example.proyecto.integrador3.incidents.infrastructure.repository;

import com.example.proyecto.integrador3.incidents.infrastructure.entity.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIncidentRepository
        extends JpaRepository<IncidentEntity, Long> {
}