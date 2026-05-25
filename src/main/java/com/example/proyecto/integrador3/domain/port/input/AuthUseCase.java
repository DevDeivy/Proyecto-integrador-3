
package com.example.proyecto.integrador3.domain.port.input;

import com.example.proyecto.integrador3.adapter.input.web.dto.AuthRequestDTO;
import com.example.proyecto.integrador3.adapter.input.web.dto.AuthResponseDTO;
import com.example.proyecto.integrador3.adapter.input.web.dto.LoginRequestDTO;

public interface AuthUseCase {
    AuthResponseDTO register(AuthRequestDTO request);
    AuthResponseDTO login(LoginRequestDTO request);
}