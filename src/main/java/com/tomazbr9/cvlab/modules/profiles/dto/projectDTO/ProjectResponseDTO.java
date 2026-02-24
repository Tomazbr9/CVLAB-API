package com.tomazbr9.cvlab.modules.profiles.dto.projectDTO;

import java.time.LocalDate;
import java.util.UUID;

public record ProjectResponseDTO(

        UUID id,

        String projectName,

        String technologiesUsed,

        String projectLocation,

        LocalDate startDate,

        LocalDate endDate,

        String description,

        String link
) {
}
