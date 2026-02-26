package com.tomazbr9.cvlab.modules.profiles.service;

import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.*;
import com.tomazbr9.cvlab.modules.profiles.mapper.ProjectMapper;
import com.tomazbr9.cvlab.modules.profiles.repository.ProfileRepository;
import com.tomazbr9.cvlab.modules.profiles.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProjectService {

    @Autowired ProjectRepository projectRepository;
    @Autowired ProjectMapper mapper;
    @Autowired ProfileRepository profileRepository;

    @Transactional
    public ProjectResponseDTO createProject(UUID profileId, ProjectDTO request, UUID userId){

        Profile profile = profileRepository.findByIdAndUser_id(profileId, userId).orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        Project project = mapper.toEntity(request);
        project.setProfile(profile);

        Project savedProject = projectRepository.save(project);

        return mapper.toDTO(savedProject);
    }

    @Transactional
    public ProjectResponseDTO updateExperience(UUID profileId, UUID projectId, ProjectUpdateDTO request, UUID userId){

        Project project = projectRepository.findByIdAndProfileIdAndProfileUserId(projectId, profileId, userId).orElseThrow(() -> new RuntimeException("Acesso negado ou recurso não encontrado"));
        mapper.updateDTOFromEntity(request, project);

        Project savedProject = projectRepository.save(project);
        return mapper.toDTO(savedProject);

    }

    @Transactional
    public void deleteProject(UUID profileId, UUID projectId, UUID userId){
        Project project = projectRepository.findByIdAndProfileIdAndProfileUserId(projectId, profileId, userId).orElseThrow(() -> new RuntimeException("Acesso negado ou recurso não encontrado"));
        projectRepository.delete(project);
    }
}
