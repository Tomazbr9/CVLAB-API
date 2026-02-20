package com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ExperienceDTO(

        @NotBlank(message = "título da experiência é obrigatório")
        String jobTitle,

        @NotBlank(message = "Nome da empresa é obrigatório")
        String companyName,

        @NotBlank(message = "Local de trabalho é obrigatório")
        String workPlace,

        @NotNull(message = "Data de inicio é obrigatória")
        LocalDate startDate,

        LocalDate endDate,

        String description
) {
}
