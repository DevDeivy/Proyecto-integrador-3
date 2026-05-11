package com.example.proyecto.integrador3.adapter.input.web.controllers;

import com.example.proyecto.integrador3.adapter.input.web.dto.AuthRequestDTO;
import com.example.proyecto.integrador3.adapter.input.web.dto.AuthResponseDTO;
import com.example.proyecto.integrador3.adapter.input.web.dto.LoginRequestDTO;
import com.example.proyecto.integrador3.domain.port.input.AuthUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(authUseCase.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authUseCase.login(request));
    }
}