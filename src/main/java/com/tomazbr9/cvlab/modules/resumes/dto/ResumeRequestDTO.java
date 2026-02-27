package com.tomazbr9.cvlab.modules.resumes.dto;

public record ResumeRequestDTO(
        ResumeDTO resume,
        String jobDescription
) {
}
