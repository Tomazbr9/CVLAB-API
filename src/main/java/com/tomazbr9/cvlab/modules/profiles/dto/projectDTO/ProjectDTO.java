package com.tomazbr9.cvlab.modules.profiles.dto.projectDTO;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ProjectDTO(

        @NotBlank(message = "Nome do projeto é obrigatório")
        String projectName,

        String technologiesUsed,

        String projectLocation,

        LocalDate startDate,

        LocalDate endDate,

        String description,

        String link
) {
}
