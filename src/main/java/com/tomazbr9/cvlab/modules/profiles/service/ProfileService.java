package com.tomazbr9.cvlab.modules.profiles.service;

import com.tomazbr9.cvlab.modules.profiles.dto.*;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.profileDTO.ProfileRequestDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.profileDTO.ProfileResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.profileDTO.ProfileUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.*;
import com.tomazbr9.cvlab.modules.profiles.mapper.*;
import com.tomazbr9.cvlab.modules.profiles.repository.*;
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
    @Autowired CourseRepository courseRepository;
    @Autowired LinkRepository linkRepository;
    @Autowired SkillRepository skillRepository;

    @Autowired ProfileMapper profileMapper;
    @Autowired ProjectMapper projectMapper;
    @Autowired ExperienceMapper experienceMapper;
    @Autowired FormationMapper formationMapper;
    @Autowired CourseMapper courseMapper;
    @Autowired LinkMapper linkMapper;
    @Autowired SkillMapper skillMapper;

    public ProfileResponseDTO getProfile(UUID profileId, UUID userId){
        Profile profile = profileRepository.findByIdAndUser_id(profileId, userId).orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        return profileMapper.toDTO(profile);
    }

    @Transactional
    public ProfileResponseDTO createProfile(ProfileRequestDTO request, UUID userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

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
        saveCourses(request.courses(), savedProfile);
        saveLinks(request.links(), savedProfile);
        saveSkills(request.skills(), savedProfile);

        return profileMapper.toDTO(savedProfile);

    }

    @Transactional
    public ProfileResponseDTO updateProfile(UUID profileId, ProfileUpdateDTO request, UUID userId){

        Profile profile = profileRepository.findByIdAndUser_id(profileId, userId).orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        profileMapper.updateEntityFromDTO(request, profile);

        profileRepository.save(profile);

        return profileMapper.toDTO(profile);
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

    private void saveCourses(List<CourseDTO> coursesDTO, Profile profile){
        List<Course> courses = courseMapper.toEntityList(coursesDTO);
        courses.forEach(course -> course.setProfile(profile));
        courseRepository.saveAll(courses);
    }

    private void saveLinks(List<LinkDTO> linksDTO, Profile profile){
        List<Link> links = linkMapper.toEntityList(linksDTO);
        links.forEach(link -> link.setProfile(profile));
        linkRepository.saveAll(links);
    }

    private void saveSkills(List<SkillDTO> skillsDTO, Profile profile){
        List<Skill> skills = skillMapper.toEntityList(skillsDTO);
        skills.forEach(skill -> skill.setProfile(profile));
        skillRepository.saveAll(skills);
    }


}
