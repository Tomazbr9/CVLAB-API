package com.tomazbr9.cvlab.modules.profiles.mapper;

import com.tomazbr9.cvlab.modules.profiles.dto.profileDTO.ProfileResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.profileDTO.ProfileUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProfileMapper {

    ProfileResponseDTO toDTO(Profile profile);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(ProfileUpdateDTO dto, @MappingTarget Profile entity);

}
