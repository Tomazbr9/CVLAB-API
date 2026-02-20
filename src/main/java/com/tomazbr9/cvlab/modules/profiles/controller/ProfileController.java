package com.tomazbr9.cvlab.modules.profiles.controller;

import com.tomazbr9.cvlab.modules.profiles.dto.profileDTO.ProfileRequestDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.profileDTO.ProfileResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.profileDTO.ProfileUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.service.ProfileService;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/profiles")
public class ProfileController {

    @Autowired private ProfileService service;

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDTO> getProfile(
            @PathVariable UUID profileId,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        ProfileResponseDTO response = service.getProfile(profileId, userDetails.getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> createProfile(
            @RequestBody ProfileRequestDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        ProfileResponseDTO response = service.createProfile(request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(
            @PathVariable UUID profileId,
            @RequestBody ProfileUpdateDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        ProfileResponseDTO response = service.updateProfile(profileId, request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
