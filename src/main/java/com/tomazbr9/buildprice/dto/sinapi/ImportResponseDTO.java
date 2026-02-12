package com.tomazbr9.buildprice.dto.sinapi;

public record ImportResponseDTO(
        Long jobId,
        Long jobExecutionId,
        String status
) {
}
