package com.tomazbr9.cvlab.modules.profiles.controller;


import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.projectDTO.ProjectUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.service.ExperienceService;
import com.tomazbr9.cvlab.modules.profiles.service.ProjectService;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/profiles/{profileId}/projects")
public class ProjectController {

    @Autowired
    ProjectService service;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(
            @PathVariable UUID profileId,
            @RequestBody ProjectDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        ProjectResponseDTO response = service.createProject(profileId, request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO> updateProject(
            @PathVariable UUID profileId,
            @PathVariable UUID projectId,
            @RequestBody ProjectUpdateDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){

        ProjectResponseDTO response = service.updateExperience(profileId, projectId, request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable UUID profileId,
            @PathVariable UUID projectId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        service.deleteProject(profileId, projectId, userDetails.getId());
        return ResponseEntity.noContent().build();

    }

}
