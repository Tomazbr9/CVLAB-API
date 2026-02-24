package com.tomazbr9.cvlab.modules.profiles.mapper;

import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ExperienceMapper {

    Experience toEntity(ExperienceDTO experienceDTO);

    ExperienceResponseDTO toDTO(Experience experience);

    List<Experience> toEntityList(List<ExperienceDTO> experiencesDTO);

    @Mapping(target = "id", ignore = true)
    void updateDTOFromEntity(ExperienceUpdateDTO dto, @MappingTarget Experience experience);
}
