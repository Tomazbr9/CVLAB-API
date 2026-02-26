package com.tomazbr9.cvlab.modules.profiles.controller;


import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.service.CourseService;
import com.tomazbr9.cvlab.modules.profiles.service.ExperienceService;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/profiles/{profileId}/courses")
public class CourseController {

    @Autowired
    CourseService service;

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(
            @PathVariable UUID profileId,
            @RequestBody CourseDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        CourseResponseDTO response = service.createCourse(profileId, request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{courseId}")
    public ResponseEntity<CourseResponseDTO> updateCourse(
            @PathVariable UUID profileId,
            @PathVariable UUID courseId,
            @RequestBody CourseUpdateDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){

        CourseResponseDTO response = service.updateCourse(profileId, courseId, request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable UUID profileId,
            @RequestBody UUID courseId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        service.deleteCourse(profileId, courseId, userDetails.getId());
        return ResponseEntity.noContent().build();
    }

}
