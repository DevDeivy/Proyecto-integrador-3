
package com.example.proyecto.integrador3.domain.port.input;

import com.example.proyecto.integrador3.adapter.input.web.dto.AuthRequestDTO;
import com.example.proyecto.integrador3.adapter.input.web.dto.AuthResponseDTO;

public interface AuthUseCase {
    AuthResponseDTO register(AuthRequestDTO request);
    AuthResponseDTO login(AuthRequestDTO request);
}