package com.tomazbr9.cvlab.modules.profiles.service;

import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Experience;
import com.tomazbr9.cvlab.modules.profiles.entity.Profile;
import com.tomazbr9.cvlab.modules.profiles.entity.Project;
import com.tomazbr9.cvlab.modules.profiles.entity.Skill;
import com.tomazbr9.cvlab.modules.profiles.mapper.ProjectMapper;
import com.tomazbr9.cvlab.modules.profiles.repository.ProfileRepository;
import com.tomazbr9.cvlab.modules.profiles.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectService {

    @Autowired ProjectRepository projectRepository;
    @Autowired ProjectMapper mapper;
    @Autowired ProfileRepository profileRepository;

    public ProjectResponseDTO createProject(UUID profileId, ProjectDTO request, UUID userId){

        Profile profile = profileRepository.findByIdAndUser_id(profileId, userId).orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        Project project = mapper.toEntity(request);
        project.setProfile(profile);

        return mapper.toDTO(project);
    }

    public ProjectResponseDTO updateExperience(UUID profileId, UUID projectId, ProjectUpdateDTO request, UUID userId){

        Project project = projectRepository.findByIdAndProfileIdAndProfileUserId(projectId, profileId, userId).orElseThrow(() -> new RuntimeException("Acesso negado ou recurso não encontrado"));
        mapper.updateDTOFromEntity(request, project);

        Project savedProject = projectRepository.save(project);
        return mapper.toDTO(savedProject);

    }
}
