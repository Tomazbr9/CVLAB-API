package com.tomazbr9.cvlab.modules.profiles.dto.profileDTO;

import com.tomazbr9.cvlab.modules.profiles.dto.*;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceDTO;

import java.util.List;

public record ProfileResponseDTO(

        String fullName,
        String email,
        String phone,
        String professionalSummary,

        List<ExperienceDTO> experiences,
        List<FormationDTO> formations,
        List<ProjectDTO> projects,
        List<SkillDTO> skills,
        List<CourseDTO> courses,
        List<LinkDTO> links
) {
}
