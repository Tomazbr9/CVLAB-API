package com.tomazbr9.cvlab.modules.profiles.controller;

import com.tomazbr9.cvlab.modules.profiles.dto.ProfileRequestDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.ProfileResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.service.ProfileService;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/profiles")
public class ProfileController {

    @Autowired private ProfileService service;

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> createProfile(
            @RequestBody ProfileRequestDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            ){

        ProfileResponseDTO response = service.createProfile(request, userDetails.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
