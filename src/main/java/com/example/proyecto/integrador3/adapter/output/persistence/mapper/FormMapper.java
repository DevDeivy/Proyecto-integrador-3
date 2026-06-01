package com.example.proyecto.integrador3.adapter.output.persistence.mapper;

import com.example.proyecto.integrador3.adapter.input.web.dto.FormRequestDTO;
import com.example.proyecto.integrador3.adapter.input.web.dto.FormResponseDTO;
import com.example.proyecto.integrador3.adapter.output.persistence.entity.Form;
import org.springframework.stereotype.Component;

@Component
public class FormMapper {

    public FormResponseDTO entityToDTO(Form form){
        if(form == null) return null;
        return FormResponseDTO.builder()
                .type(form.getTypeRequest())
                .priority(form.getPriority())
                .description(form.getDescription())
                .build();
    }

    public Form dtoToEntity(FormRequestDTO formRequestDTO){
        if (formRequestDTO == null) return null;
        Form form = new Form();
        form.setTypeRequest(formRequestDTO.getTypeRequest());
        form.setSection(formRequestDTO.getSection());
        form.setDescription(formRequestDTO.getDescription());
        form.setPriority(formRequestDTO.getPriority());
        form.setCategory(formRequestDTO.getCategory());
        return form;
    }
}
