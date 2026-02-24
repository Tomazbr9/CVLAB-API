package com.tomazbr9.cvlab.modules.profiles.dto.formationDTO;

import com.tomazbr9.cvlab.modules.profiles.enums.FormationStatus;

import java.time.LocalDate;
import java.util.UUID;

public record FormationResponseDTO(

        UUID id,

        String courseName,

        String institutionName,

        String courseLocation,

        LocalDate startDate,

        LocalDate endDate,

        String description,

        FormationStatus formationStatus

) {
}
