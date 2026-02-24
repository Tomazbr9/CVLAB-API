package com.tomazbr9.cvlab.modules.profiles.controller;


import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.formationDTO.FormationResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.service.FormationService;
import com.tomazbr9.cvlab.modules.profiles.service.SkillService;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/profiles/{profileId}/skills")
public class SkillController {

    @Autowired
    SkillService service;

    @PostMapping
    public ResponseEntity<SkillResponseDTO> createSkill(
            @PathVariable UUID profileId,
            @RequestBody SkillDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        SkillResponseDTO response = service.createSkill(profileId, request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{skillId}")
    public ResponseEntity<SkillResponseDTO> updateSkill(
            @PathVariable UUID profileId,
            @PathVariable UUID skillId,
            @RequestBody SkillUpdateDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){

        SkillResponseDTO response = service.updateSkill(profileId, skillId, request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

    }

}
