package com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ExperienceUpdateDTO(

        String jobTitle,

        String companyName,

        String workPlace,

        LocalDate startDate,

        LocalDate endDate,

        String description
) {}
