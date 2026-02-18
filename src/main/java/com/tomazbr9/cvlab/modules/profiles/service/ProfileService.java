package com.tomazbr9.cvlab.modules.profiles.service;

import com.tomazbr9.cvlab.modules.profiles.dto.*;
import com.tomazbr9.cvlab.modules.profiles.entity.Experience;
import com.tomazbr9.cvlab.modules.profiles.entity.Formation;
import com.tomazbr9.cvlab.modules.profiles.entity.Profile;
import com.tomazbr9.cvlab.modules.profiles.entity.Project;
import com.tomazbr9.cvlab.modules.profiles.mapper.ExperienceMapper;
import com.tomazbr9.cvlab.modules.profiles.mapper.FormationMapper;
import com.tomazbr9.cvlab.modules.profiles.mapper.ProjectMapper;
import com.tomazbr9.cvlab.modules.profiles.repository.ExperienceRepository;
import com.tomazbr9.cvlab.modules.profiles.repository.FormationRepository;
import com.tomazbr9.cvlab.modules.profiles.repository.ProfileRepository;
import com.tomazbr9.cvlab.modules.profiles.repository.ProjectRepository;
import com.tomazbr9.cvlab.modules.users.entity.User;
import com.tomazbr9.cvlab.modules.users.exception.UserNotFoundException;
import com.tomazbr9.cvlab.modules.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfileService {

    @Autowired ProfileRepository profileRepository;
    @Autowired UserRepository userRepository;
    @Autowired FormationRepository formationRepository;
    @Autowired ExperienceRepository experienceRepository;
    @Autowired ProjectRepository projectRepository;
    @Autowired ProjectMapper projectMapper;
    @Autowired ExperienceMapper experienceMapper;
    @Autowired FormationMapper formationMapper;


    @Transactional
    public ProfileResponseDTO createProfile(ProfileRequestDTO request, UUID userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"))

        Profile profile = Profile.builder()
                .fullName(request.fullName())
                .email(request.email())
                .phone(request.phone())
                .professionalSummary(request.professionalSummary())
                .user(user)
                .build();

        Profile savedProfile = profileRepository.save(profile);

        saveExperiences(request.experiences(), savedProfile);
        saveFormations(request.formations(), savedProfile);
        saveProjects(request.projects(), savedProfile);


    }

    private void saveExperiences(List<ExperienceDTO> experiencesDTO, Profile profile){
        List<Experience> experiences = experienceMapper.toEntityList(experiencesDTO);
        experiences.forEach(experience -> experience.setProfile(profile));
        experienceRepository.saveAll(experiences);
    }

    private void saveFormations(List<FormationDTO> formationsDTO, Profile profile){
        List<Formation> formations = formationMapper.toEntityList(formationsDTO);
        formations.forEach(formation -> formation.setProfile(profile));
        formationRepository.saveAll(formations);
    }
     private void saveProjects(List<ProjectDTO> projectsDTO, Profile profile){
        List<Project> projects = projectMapper.toEntityList(projectsDTO);
        projects.forEach(project -> project.setProfile(profile));
        projectRepository.saveAll(projects);
     }

}
