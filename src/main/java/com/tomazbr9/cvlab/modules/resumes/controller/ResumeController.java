package com.tomazbr9.cvlab.modules.resumes.controller;

import com.tomazbr9.cvlab.modules.resumes.dto.ResumeRequestDTO;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeResponseDTO;
import com.tomazbr9.cvlab.modules.resumes.service.ResumeService;
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
@RequestMapping("api/v1/resumes")
public class ResumeController {

    @Autowired
    ResumeService service;

    @PostMapping("/optimized")
    public ResponseEntity<ResumeResponseDTO> optimizedResume(
            @RequestBody ResumeRequestDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        ResumeResponseDTO response = service.optimizedResume(request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
