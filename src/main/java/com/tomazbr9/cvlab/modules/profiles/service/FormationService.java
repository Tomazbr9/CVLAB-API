package com.tomazbr9.cvlab.modules.profiles.service;

import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.formationDTO.FormationDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.formationDTO.FormationResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.formationDTO.FormationUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Experience;
import com.tomazbr9.cvlab.modules.profiles.entity.Formation;
import com.tomazbr9.cvlab.modules.profiles.entity.Profile;
import com.tomazbr9.cvlab.modules.profiles.mapper.FormationMapper;
import com.tomazbr9.cvlab.modules.profiles.repository.FormationRepository;
import com.tomazbr9.cvlab.modules.profiles.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FormationService {

    @Autowired FormationRepository formationRepository;
    @Autowired FormationMapper mapper;
    @Autowired ProfileRepository profileRepository;

    @Transactional
    public FormationResponseDTO createFormation(UUID profileId, FormationDTO request, UUID userId){

        Profile profile = profileRepository.findByIdAndUser_id(profileId, userId).orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        Formation formation = mapper.toEntity(request);
        formation.setProfile(profile);

        Formation savedFormation = formationRepository.save(formation);

        return mapper.toDTO(savedFormation);
    }

    @Transactional
    public FormationResponseDTO updateFormation(UUID profileId, UUID formationId, FormationUpdateDTO request, UUID userId){

        Formation formation = formationRepository.findByIdAndProfileIdAndProfileUserId(formationId, profileId, userId).orElseThrow(() -> new RuntimeException("Acesso negado ou recurso não encontrado"));
        mapper.updateDTOFromEntity(request, formation);

        Formation savedFormation = formationRepository.save(formation);
        return mapper.toDTO(savedFormation);

    }

    @Transactional
    public void deleteFormation(UUID profileId, UUID formationId, UUID userId){
        Formation formation = formationRepository.findByIdAndProfileIdAndProfileUserId(formationId, profileId, userId).orElseThrow(() -> new RuntimeException("Acesso negado ou recurso não encontrado"));
        formationRepository.delete(formation);
    }
}
