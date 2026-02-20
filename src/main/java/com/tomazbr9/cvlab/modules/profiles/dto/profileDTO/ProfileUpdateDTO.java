package com.tomazbr9.cvlab.modules.profiles.dto.profileDTO;

public record ProfileUpdateDTO(

        String fullName,
        String email,
        String phone,
        String professionalSummary
) {}
