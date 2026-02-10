package com.tomazbr9.buildprice.dto.sinapi;

public record BatchStatusDTO(
        String status,
        Long readCount,
        Long writeCount,
        Long commitCount,
        String exitCode
) {
}
