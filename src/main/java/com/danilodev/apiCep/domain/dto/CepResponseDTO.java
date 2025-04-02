package com.danilodev.apiCep.domain.dto;

import java.time.LocalDateTime;

public record CepResponseDTO(
        String cep,
        String responseData,
        LocalDateTime timestamp
) {
}
