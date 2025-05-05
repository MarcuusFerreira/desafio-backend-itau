package com.desafio.itau.statistics.infra.dto.response;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message
) {
}
