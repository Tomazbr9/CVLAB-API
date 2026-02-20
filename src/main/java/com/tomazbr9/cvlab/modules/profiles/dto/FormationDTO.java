package com.tomazbr9.cvlab.modules.profiles.dto;

import com.tomazbr9.cvlab.modules.profiles.enums.FormationStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FormationDTO(

        @NotBlank(message = "Nome do curso é obrigatório")
        String courseName,

        @NotBlank(message = "Nome da instituição é obrigatório")
        String institutionName,

        String courseLocation,

        @NotNull(message = "Data de inicio é obrigatório")
        LocalDate startDate,

        LocalDate endDate,

        String description,

        @NotBlank(message = "Status da formação é obrigatório")
        FormationStatus formationStatus
) {
}
