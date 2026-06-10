package com.example.proyecto.integrador3.incidents.infrastructure.adapter;

import com.example.proyecto.integrador3.adapter.output.persistence.entity.User;
import com.example.proyecto.integrador3.adapter.output.persistence.repository.UserRepository;
import com.example.proyecto.integrador3.incidents.enums.IncidentState;
import com.example.proyecto.integrador3.incidents.infrastructure.entity.IncidentEntity;
import com.example.proyecto.integrador3.incidents.infrastructure.repository.JpaIncidentRepository;
import com.example.proyecto.integrador3.incidents.domain.model.Incident;
import com.example.proyecto.integrador3.incidents.domain.ports.IncidentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IncidentRepositoryAdapter implements IncidentRepositoryPort {

    private final JpaIncidentRepository jpaIncidentRepository;
    private final UserRepository userRepository;

    @Override
    public Incident save(Incident incident) {
        User user = userRepository.findById(incident.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        IncidentEntity entity = IncidentEntity.builder()
                .title(incident.getTitle())
                .description(incident.getDescription())
                .date(LocalDate.now())
                .deadline(LocalDate.now().plusWeeks(2))
                .resolutionDate(null)
                .updatedAt(LocalDate.now())
                .category(incident.getCategory())
                .state(IncidentState.OPEN)
                .address(incident.getAddress())
                .priority(incident.getPriority())
                .user(user)
                .build();

        IncidentEntity saved = jpaIncidentRepository.save(entity);
        return mapToModel(saved);
    }

    @Override
    public List<Incident> findAll() {
        return jpaIncidentRepository.findAll()
                .stream()
                .map(this::mapToModel)
                .toList();
    }

    @Override
    public Optional<Incident> findById(Long id) {
        return jpaIncidentRepository.findById(id)
                .map(this::mapToModel);
    }

    @Override
    public Incident updateState(Long id, IncidentState newState) {
        IncidentEntity entity = jpaIncidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));

        entity.setState(newState);
        entity.setUpdatedAt(LocalDate.now());

        if (newState == IncidentState.RESOLVED) {
            entity.setResolutionDate(LocalDate.now());
        }

        return mapToModel(jpaIncidentRepository.save(entity));
    }

    private Incident mapToModel(IncidentEntity saved) {
        return Incident.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .description(saved.getDescription())
                .date(saved.getDate())
                .deadline(saved.getDeadline())
                .resolutionDate(saved.getResolutionDate())
                .updatedAt(saved.getUpdatedAt())
                .category(saved.getCategory())
                .state(saved.getState())
                .address(saved.getAddress())
                .priority(saved.getPriority())
                .userId(saved.getUser().getId())
                .build();
    }
}