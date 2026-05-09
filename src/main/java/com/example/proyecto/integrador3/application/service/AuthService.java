package com.example.proyecto.integrador3.application.service;

import com.example.proyecto.integrador3.adapter.input.web.dto.AuthRequestDTO;
import com.example.proyecto.integrador3.adapter.input.web.dto.AuthResponseDTO;
import com.example.proyecto.integrador3.adapter.output.persistence.entity.User;
import com.example.proyecto.integrador3.adapter.output.persistence.repository.UserRepository;
import com.example.proyecto.integrador3.domain.port.input.AuthUseCase;
import com.example.proyecto.integrador3.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO register(AuthRequestDTO request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return AuthResponseDTO.builder().token(token).build();
    }

    @Override
    public AuthResponseDTO login(AuthRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtService.generateToken(user);
        return AuthResponseDTO.builder().token(token).build();
    }
}