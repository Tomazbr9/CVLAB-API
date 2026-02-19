package com.tomazbr9.cvlab.modules.profiles.mapper;

import com.tomazbr9.cvlab.modules.profiles.dto.ProjectDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.SkillDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Project;
import com.tomazbr9.cvlab.modules.profiles.entity.Skill;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    Project toEntity(ProjectDTO projectDTO);

    List<Project> toEntityList(List<ProjectDTO> projectsDTO);
}
