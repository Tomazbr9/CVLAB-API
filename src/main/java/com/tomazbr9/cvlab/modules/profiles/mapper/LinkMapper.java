package com.tomazbr9.cvlab.modules.profiles.mapper;

import com.tomazbr9.cvlab.modules.profiles.dto.linkDTO.LinkDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.linkDTO.LinkResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.linkDTO.LinkUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Link;
import com.tomazbr9.cvlab.modules.profiles.entity.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LinkMapper {

    Link toEntity(LinkDTO linkDTO);

    List<Link> toEntityList(List<LinkDTO> linksDTO);

    LinkResponseDTO toDTO(Link entity);

    @Mapping(target = "id", ignore = true)
    void updateDTOFromEntity(LinkUpdateDTO dto, @MappingTarget Link entity);
}
