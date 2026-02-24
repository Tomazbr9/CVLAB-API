package com.tomazbr9.cvlab.modules.profiles.mapper;

import com.tomazbr9.cvlab.modules.profiles.dto.formationDTO.FormationDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.formationDTO.FormationResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.formationDTO.FormationUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Formation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FormationMapper {

    Formation toEntity(FormationDTO formationDTO);

    FormationResponseDTO toDTO(Formation formation);

    List<Formation> toEntityList(List<FormationDTO> formationsDTO);

    @Mapping(target = "id", ignore = true)
    void updateDTOFromEntity(FormationUpdateDTO dto, @MappingTarget Formation entity);
}
