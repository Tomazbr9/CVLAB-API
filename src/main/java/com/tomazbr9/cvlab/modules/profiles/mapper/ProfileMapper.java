package com.tomazbr9.cvlab.modules.profiles.mapper;

import com.tomazbr9.cvlab.modules.profiles.dto.ProfileResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileResponseDTO toDTO(Profile profile);

}
