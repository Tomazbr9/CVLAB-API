package com.tomazbr9.cvlab.modules.profiles.service;

import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Course;
import com.tomazbr9.cvlab.modules.profiles.entity.Experience;
import com.tomazbr9.cvlab.modules.profiles.entity.Profile;
import com.tomazbr9.cvlab.modules.profiles.mapper.CourseMapper;
import com.tomazbr9.cvlab.modules.profiles.repository.CourseRepository;
import com.tomazbr9.cvlab.modules.profiles.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CourseService {

    @Autowired CourseRepository courseRepository;
    @Autowired CourseMapper mapper;
    @Autowired ProfileRepository profileRepository;

    public CourseResponseDTO createCourse(UUID profileId, CourseDTO request, UUID userId){

        Profile profile = profileRepository.findByIdAndUser_id(profileId, userId).orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        Course course = mapper.toEntity(request);
        course.setProfile(profile);

        // Precisa salvar os objetos!

        return mapper.toDTO(course);
    }

    public CourseResponseDTO updateCourse(UUID profileId, UUID courseId, CourseUpdateDTO request, UUID userId){

        Course course = courseRepository.findByIdAndProfileIdAndProfileUserId(courseId, profileId, userId).orElseThrow(() -> new RuntimeException("Acesso negado ou recurso não encontrado"));
        mapper.updateDTOFromEntity(request, course);

        Course savedCourse = courseRepository.save(course);
        return mapper.toDTO(savedCourse);

    }
}
