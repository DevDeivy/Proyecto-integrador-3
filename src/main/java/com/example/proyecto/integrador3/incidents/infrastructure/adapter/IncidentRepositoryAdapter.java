package com.example.proyecto.integrador3.incidents.infrastructure.adapter;

import com.example.proyecto.integrador3.adapter.output.persistence.entity.User;
import com.example.proyecto.integrador3.adapter.output.persistence.repository.UserRepository;
import com.example.proyecto.integrador3.incidents.infrastructure.entity.IncidentEntity;
import com.example.proyecto.integrador3.incidents.infrastructure.repository.JpaIncidentRepository;
import com.example.proyecto.integrador3.incidents.domain.model.Incident;
import com.example.proyecto.integrador3.incidents.domain.ports.IncidentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

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
                .updatedAt(null)
                .categoryId(incident.getCategoryId())
                .stateId(incident.getStateId())
                .addressId(incident.getAddressId())
                .priorityId(incident.getPriorityId())
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

    private Incident mapToModel(IncidentEntity saved) {
        return Incident.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .description(saved.getDescription())
                .date(saved.getDate())
                .deadline(saved.getDeadline())
                .resolutionDate(saved.getResolutionDate())
                .updatedAt(saved.getUpdatedAt())
                .categoryId(saved.getCategoryId())
                .stateId(saved.getStateId())
                .addressId(saved.getAddressId())
                .priorityId(saved.getPriorityId())
                .userId(saved.getUser().getId())
                .build();
    }
}