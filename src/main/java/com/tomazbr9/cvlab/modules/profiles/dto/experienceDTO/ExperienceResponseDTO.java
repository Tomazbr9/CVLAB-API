package com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ExperienceResponseDTO(

        UUID id,

        String jobTitle,

        String companyName,

        String workPlace,

        LocalDate startDate,

        LocalDate endDate,

        String description
) {
}
