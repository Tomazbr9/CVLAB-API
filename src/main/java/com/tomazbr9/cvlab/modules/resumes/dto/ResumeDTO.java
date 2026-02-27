package com.tomazbr9.cvlab.modules.resumes.dto;

import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.formationDTO.FormationDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.linkDTO.LinkDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillDTO;

import java.util.List;

public record ResumeDTO(

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

    public List<ExperienceDTO> experiences(){
        return experiences == null ? List.of() : experiences;
    }

    public List<FormationDTO> formations(){
        return formations == null ? List.of() : formations;
    }

    public List<ProjectDTO> projects(){
        return projects == null ? List.of() : projects;
    }

    public List<SkillDTO> skills(){
        return skills == null ? List.of() : skills;
    }

    public List<CourseDTO> courses(){
        return courses == null ? List.of() : courses;
    }

    public List<LinkDTO> links(){
        return links == null ? List.of() : links;
    }
}
