package com.tomazbr9.cvlab.modules.profiles.dto.profileDTO;

import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.formationDTO.FormationDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.formationDTO.FormationResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.linkDTO.LinkDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.linkDTO.LinkResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillResponseDTO;

import java.util.List;

public record ProfileResponseDTO(

        String fullName,
        String email,
        String phone,
        String professionalSummary,

        List<ExperienceResponseDTO> experiences,
        List<FormationResponseDTO> formations,
        List<ProjectResponseDTO> projects,
        List<SkillResponseDTO> skills,
        List<CourseResponseDTO> courses,
        List<LinkResponseDTO> links
) {
}
