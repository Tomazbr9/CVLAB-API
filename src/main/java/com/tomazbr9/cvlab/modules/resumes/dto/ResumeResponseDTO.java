package com.tomazbr9.cvlab.modules.resumes.dto;

import java.time.Instant;
import java.util.UUID;

public record ResumeResponseDTO(
        UUID id,
        ResumeDTO optimizedJson,
        String jobDescription,
        boolean isPaidSingle,
        Instant createdAt,
        Instant updateAt
) {
}
