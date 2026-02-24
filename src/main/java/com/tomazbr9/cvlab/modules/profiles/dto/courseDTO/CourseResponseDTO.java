package com.tomazbr9.cvlab.modules.profiles.dto.courseDTO;

import java.time.LocalDate;
import java.util.UUID;

public record CourseResponseDTO(

        UUID id,

        String courseName,

        String institutionName,

        String courseLocation,

        LocalDate startDate,

        LocalDate endDate,

        Integer workLoad,

        String description
) {
}
