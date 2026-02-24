package com.tomazbr9.cvlab.modules.profiles.service;

import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.linkDTO.LinkDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.linkDTO.LinkResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.linkDTO.LinkUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Course;
import com.tomazbr9.cvlab.modules.profiles.entity.Link;
import com.tomazbr9.cvlab.modules.profiles.entity.Profile;
import com.tomazbr9.cvlab.modules.profiles.entity.Skill;
import com.tomazbr9.cvlab.modules.profiles.mapper.CourseMapper;
import com.tomazbr9.cvlab.modules.profiles.mapper.LinkMapper;
import com.tomazbr9.cvlab.modules.profiles.repository.CourseRepository;
import com.tomazbr9.cvlab.modules.profiles.repository.LinkRepository;
import com.tomazbr9.cvlab.modules.profiles.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LinkService {

    @Autowired LinkRepository linkRepository;
    @Autowired LinkMapper mapper;
    @Autowired ProfileRepository profileRepository;

    public LinkResponseDTO createLink(UUID profileId, LinkDTO request, UUID userId){

        Profile profile = profileRepository.findByIdAndUser_id(profileId, userId).orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        Link link = mapper.toEntity(request);
        link.setProfile(profile);

        return mapper.toDTO(link);
    }

    public LinkResponseDTO updateCourse(UUID profileId, UUID linkId, LinkUpdateDTO request, UUID userId){

        Link link = linkRepository.findByIdAndProfileIdAndProfileUserId(linkId, profileId, userId).orElseThrow(() -> new RuntimeException("Acesso negado ou recurso não encontrado"));
        mapper.updateDTOFromEntity(request, link);

        Link savedLink = linkRepository.save(link);
        return mapper.toDTO(savedLink);

    }
}
