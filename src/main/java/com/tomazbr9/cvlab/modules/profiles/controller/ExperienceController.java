package com.tomazbr9.cvlab.modules.profiles.controller;


import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.service.ExperienceService;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/profiles/{profileId}/experiences")
public class ExperienceController {

    @Autowired
    ExperienceService service;

    @PostMapping
    public ResponseEntity<ExperienceResponseDTO> createExperience(
            @PathVariable UUID profileId,
            @RequestBody ExperienceDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        ExperienceResponseDTO response = service.createExperience(profileId, request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{experienceId}")
    public ResponseEntity<ExperienceResponseDTO> updateExperience(
            @PathVariable UUID profileId,
            @PathVariable UUID experienceId,
            @RequestBody ExperienceUpdateDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        ExperienceResponseDTO response = service.updateExperience(profileId, experienceId, request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

    }

    @DeleteMapping("/{experienceId}")
    public ResponseEntity<Void> deleteExperience(
            @PathVariable UUID profileId,
            @PathVariable UUID experienceId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        service.deleteExperience(profileId, experienceId, userDetails.getId());
        return ResponseEntity.noContent().build();

    }

}
