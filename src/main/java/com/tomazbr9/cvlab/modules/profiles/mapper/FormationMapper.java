package com.tomazbr9.cvlab.modules.profiles.mapper;

import com.tomazbr9.cvlab.modules.profiles.dto.FormationDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Formation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormationMapper {

    Formation toEntity(FormationDTO formationDTO);

    List<Formation> toEntityList(List<FormationDTO> formationsDTO);
}
