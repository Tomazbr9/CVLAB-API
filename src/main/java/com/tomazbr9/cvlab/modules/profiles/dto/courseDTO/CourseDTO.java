package com.tomazbr9.cvlab.modules.profiles.dto.courseDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CourseDTO(

        @NotBlank(message = "nome do curso ou certificação é obrigatório")
        String courseName,

        @NotBlank(message = "nome da instituição é obrigatório ")
        String institutionName,

        String courseLocation,

        @NotNull(message = "Data de inicio é obrigatório")
        LocalDate startDate,

        LocalDate endDate,

        @NotNull(message = "Carga horária é obrigatória")
        Integer workLoad,

        String description
) {
}
