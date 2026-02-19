package com.tomazbr9.cvlab.modules.profiles.mapper;

import com.tomazbr9.cvlab.modules.profiles.dto.FormationDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.SkillDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Formation;
import com.tomazbr9.cvlab.modules.profiles.entity.Skill;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    Skill toEntity(SkillDTO skillDTO);

    List<Skill> toEntityList(List<SkillDTO> skillsDTO);
}
