package com.tomazbr9.cvlab.modules.resumes.dto;

public record ResumeRequestDTO(
        String nameResume,
        ResumeDTO resume,
        String jobDescription
) {
}
