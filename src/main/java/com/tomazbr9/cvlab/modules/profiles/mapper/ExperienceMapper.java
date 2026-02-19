package com.tomazbr9.cvlab.modules.profiles.mapper;

import com.tomazbr9.cvlab.modules.profiles.dto.ExperienceDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Experience;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {

    Experience toEntity(ExperienceDTO experienceDTO);

    List<Experience> toEntityList(List<ExperienceDTO> experiencesDTO);
}
