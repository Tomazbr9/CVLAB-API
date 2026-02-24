package com.tomazbr9.cvlab.modules.profiles.mapper;

import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.formationDTO.FormationUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Experience;
import com.tomazbr9.cvlab.modules.profiles.entity.Formation;
import com.tomazbr9.cvlab.modules.profiles.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectMapper {

    Project toEntity(ProjectDTO projectDTO);

    List<Project> toEntityList(List<ProjectDTO> projectsDTO);

    ProjectResponseDTO toDTO(Project entity);

    @Mapping(target = "id", ignore = true)
    void updateDTOFromEntity(ProjectUpdateDTO dto, @MappingTarget Project entity);
}
