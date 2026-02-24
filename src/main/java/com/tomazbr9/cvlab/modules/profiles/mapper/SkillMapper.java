package com.tomazbr9.cvlab.modules.profiles.mapper;

import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Experience;
import com.tomazbr9.cvlab.modules.profiles.entity.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SkillMapper {

    Skill toEntity(SkillDTO skillDTO);

    List<Skill> toEntityList(List<SkillDTO> skillsDTO);

    SkillResponseDTO toDTO(Skill entity);

    @Mapping(target = "id", ignore = true)
    void updateDTOFromEntity(SkillUpdateDTO dto, @MappingTarget Skill entity);
}
