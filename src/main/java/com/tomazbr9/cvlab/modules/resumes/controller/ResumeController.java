package com.tomazbr9.cvlab.modules.resumes.controller;

import com.tomazbr9.cvlab.modules.resumes.dto.ResumeDTO;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeRequestDTO;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeOptimizedResponseDTO;
import com.tomazbr9.cvlab.modules.resumes.dto.ResumeResponseDTO;
import com.tomazbr9.cvlab.modules.resumes.service.ResumeService;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/resumes")
public class ResumeController {

    @Autowired
    ResumeService service;

    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeResponseDTO> getResume(@PathVariable UUID resumeId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        ResumeResponseDTO response = service.getResume(resumeId, userDetails.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ResumeResponseDTO>> getResumes(@AuthenticationPrincipal UserDetailsImpl userDetails){
        List<ResumeResponseDTO> response = service.getResumes(userDetails.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/optimized")
    public ResponseEntity<ResumeOptimizedResponseDTO> optimizedResume(
            @RequestBody ResumeRequestDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        ResumeOptimizedResponseDTO response = service.optimizedResume(request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping
    public ResponseEntity<ResumeResponseDTO> createResume(
            @RequestBody ResumeRequestDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        ResumeResponseDTO response = service.createResume(request, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{resumeId}")
    public ResponseEntity<ResumeResponseDTO> updateResume(
            @PathVariable UUID resumeId,
            @RequestBody ResumeDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        ResumeResponseDTO response = service.updateResume(resumeId, request, userDetails.getId());
        return ResponseEntity.accepted().body(response);
    }

    @DeleteMapping("/{resumeId}")
    public ResponseEntity<Void> deleteResume(
            @PathVariable UUID resumeId,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        service.deleteResume(resumeId, userDetails.getId());
        return ResponseEntity.noContent().build();
    }
}
