package com.tomazbr9.cvlab.modules.profiles.service;

import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Experience;
import com.tomazbr9.cvlab.modules.profiles.entity.Profile;
import com.tomazbr9.cvlab.modules.profiles.mapper.ExperienceMapper;
import com.tomazbr9.cvlab.modules.profiles.repository.ExperienceRepository;
import com.tomazbr9.cvlab.modules.profiles.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExperienceService {

    @Autowired ExperienceRepository experienceRepository;
    @Autowired ExperienceMapper mapper;
    @Autowired ProfileRepository profileRepository;

    public ExperienceResponseDTO createExperience(UUID profileId, ExperienceDTO request, UUID userId){

        Profile profile = profileRepository.findByIdAndUser_id(profileId, userId).orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        Experience experience = mapper.toEntity(request);
        experience.setProfile(profile);

        return mapper.toDTO(experience);
    }

    public ExperienceResponseDTO updateExperience(UUID profileId, UUID experienceId, ExperienceUpdateDTO request, UUID userId){

        Experience experience = experienceRepository.findByIdAndProfileIdAndProfileUserId(experienceId, profileId, userId).orElseThrow(() -> new RuntimeException("Acesso negado ou recurso não encontrado"));
        mapper.updateDTOFromEntity(request, experience);

        Experience savedExperience = experienceRepository.save(experience);
        return mapper.toDTO(savedExperience);

    }
}
