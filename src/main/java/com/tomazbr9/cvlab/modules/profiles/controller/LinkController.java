package com.tomazbr9.cvlab.modules.profiles.controller;


import com.tomazbr9.cvlab.modules.profiles.dto.linkDTO.LinkDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.linkDTO.LinkResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.linkDTO.LinkUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.skillDTO.SkillUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.service.LinkService;
import com.tomazbr9.cvlab.modules.profiles.service.SkillService;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/profiles/{profileId}/links")
public class LinkController {

    @Autowired
    LinkService service;

    @PostMapping
    public ResponseEntity<LinkResponseDTO> createLink(
            @PathVariable UUID profileId,
            @RequestBody LinkDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        LinkResponseDTO response = service.createLink(profileId, request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{linkId}")
    public ResponseEntity<LinkResponseDTO> updateLink(
            @PathVariable UUID profileId,
            @PathVariable UUID linkId,
            @RequestBody LinkUpdateDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){

        LinkResponseDTO response = service.updateLink(profileId, linkId, request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

    }

    @DeleteMapping("/{linkId}")
    public ResponseEntity<Void> deleteLink(
            @PathVariable UUID profileId,
            @PathVariable UUID linkId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        service.deleteLink(profileId, linkId, userDetails.getId());
        return ResponseEntity.noContent().build();

    }
}
