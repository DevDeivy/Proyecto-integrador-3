package com.example.proyecto.integrador3.application.service;

import com.example.proyecto.integrador3.adapter.input.web.dto.FormRequestDTO;
import com.example.proyecto.integrador3.adapter.input.web.dto.FormResponseDTO;
import com.example.proyecto.integrador3.adapter.output.persistence.entity.Form;
import com.example.proyecto.integrador3.adapter.output.persistence.mapper.FormMapper;
import com.example.proyecto.integrador3.adapter.output.persistence.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;
    private final FormMapper formMapper;

    public List<FormResponseDTO> getAllForms(){
        return formRepository.findAll()
                .stream()
                .map(formMapper::entityToDTO)
                .toList();
    }

    public FormResponseDTO createForm(FormRequestDTO formRequestDTO){
        if (formRequestDTO == null) return null;
        Form form = formMapper.dtoToEntity(formRequestDTO);
        Form saved = formRepository.save(form);
        return formMapper.entityToDTO(saved);
    }
}
