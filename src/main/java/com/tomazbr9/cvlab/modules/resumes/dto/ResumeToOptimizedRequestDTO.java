package com.tomazbr9.cvlab.modules.resumes.dto;

import java.util.UUID;

public record ResumeToOptimizedRequestDTO(
        UUID id,
        String jobDescription
) {
}
