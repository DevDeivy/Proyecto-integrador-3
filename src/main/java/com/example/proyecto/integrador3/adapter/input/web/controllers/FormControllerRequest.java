package com.example.proyecto.integrador3.adapter.input.web.controllers;

import com.example.proyecto.integrador3.adapter.input.web.dto.FormRequestDTO;
import com.example.proyecto.integrador3.adapter.input.web.dto.FormResponseDTO;
import com.example.proyecto.integrador3.application.service.FormService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FormControllerRequest {

    private final FormService formService;

    @GetMapping("/form")
    public ResponseEntity<List<FormResponseDTO>> getAllForms(){
        return ResponseEntity.ok().body(formService.getAllForms());
    }

    @PostMapping("/form")
    public ResponseEntity<FormResponseDTO> createForm(@Valid @RequestBody FormRequestDTO formRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(formService.createForm(formRequestDTO));
    }
}
