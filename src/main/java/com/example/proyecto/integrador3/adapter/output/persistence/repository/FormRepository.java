package com.example.proyecto.integrador3.adapter.output.persistence.repository;

import com.example.proyecto.integrador3.adapter.output.persistence.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

}
