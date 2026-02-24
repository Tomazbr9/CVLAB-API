package com.tomazbr9.cvlab.modules.profiles.controller;


import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.formationDTO.FormationDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.formationDTO.FormationResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.formationDTO.FormationUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.service.FormationService;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/profiles/{profileId}/formations")
public class FormationController {

    @Autowired
    FormationService service;

    @PostMapping
    public ResponseEntity<FormationResponseDTO> createFormation(
            @PathVariable UUID profileId,
            @RequestBody FormationDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        FormationResponseDTO response = service.createFormation(profileId, request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{formationId}")
    public ResponseEntity<FormationResponseDTO> updateFormation(
            @PathVariable UUID profileId,
            @PathVariable UUID formationId,
            @RequestBody FormationUpdateDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){

        FormationResponseDTO response = service.updateFormation(profileId, formationId, request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

    }

}
