package com.tomazbr9.cvlab.modules.profiles.service;

import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Profile;
import com.tomazbr9.cvlab.modules.profiles.entity.Skill;
import com.tomazbr9.cvlab.modules.profiles.mapper.SkillMapper;
import com.tomazbr9.cvlab.modules.profiles.repository.ProfileRepository;
import com.tomazbr9.cvlab.modules.profiles.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SkillService {

    @Autowired SkillRepository skillRepository;
    @Autowired SkillMapper mapper;
    @Autowired ProfileRepository profileRepository;

    public SkillResponseDTO createSkill(UUID profileId, SkillDTO request, UUID userId){

        Profile profile = profileRepository.findByIdAndUser_id(profileId, userId).orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        Skill skill = mapper.toEntity(request);
        skill.setProfile(profile);

        return mapper.toDTO(skill);
    }

    public SkillResponseDTO updateSkill(UUID profileId, UUID skillId, SkillUpdateDTO request, UUID userId){

        Skill skill = skillRepository.findByIdAndProfileIdAndProfileUserId(skillId, profileId, userId).orElseThrow(() -> new RuntimeException("Acesso negado ou recurso não encontrado"));
        mapper.updateDTOFromEntity(request, skill);

        Skill savedSkill = skillRepository.save(skill);
        return mapper.toDTO(savedSkill);

    }
}
