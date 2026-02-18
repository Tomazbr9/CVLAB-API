package com.tomazbr9.cvlab.modules.profiles.mapper;

import com.tomazbr9.cvlab.modules.profiles.dto.LinkDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.SkillDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Link;
import com.tomazbr9.cvlab.modules.profiles.entity.Skill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LinkMapper {

    Link toEntity(LinkDTO linkDTO);
}
