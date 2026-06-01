package com.example.proyecto.integrador3.domain.port.input;

import com.example.proyecto.integrador3.adapter.input.web.dto.FormRequestDTO;
import com.example.proyecto.integrador3.adapter.input.web.dto.FormResponseDTO;

import java.util.List;

public interface CreateFormUseCase {
    FormResponseDTO createForm(FormRequestDTO request);
    List<FormResponseDTO> getAllForms();
}
