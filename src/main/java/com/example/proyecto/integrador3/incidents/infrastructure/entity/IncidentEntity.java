package com.example.proyecto.integrador3.incidents.infrastructure.entity;

import com.example.proyecto.integrador3.adapter.output.persistence.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_incident")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncidentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    private LocalDate date;

    private LocalDate deadline;

    @Column(name = "resolution_date")
    private LocalDate resolutionDate;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String categoryId;

    private String stateId;

    private String addressId;

    private String priorityId;
}