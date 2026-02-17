package com.tomazbr9.cvlab.dto.exception;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        String message,
        int status,
        String path,
        LocalDateTime timestamp

) {
}
