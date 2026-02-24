package com.tomazbr9.cvlab.modules.profiles.dto.courseDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CourseUpdateDTO(

        String courseName,

        String institutionName,

        String courseLocation,

        LocalDate startDate,

        LocalDate endDate,

        Integer workLoad,

        String description
) {
}
