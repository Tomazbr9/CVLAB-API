package com.tomazbr9.buildprice.controller;

import com.tomazbr9.buildprice.dto.project.ProjectRequestDTO;
import com.tomazbr9.buildprice.dto.project.ProjectResponseDTO;
import com.tomazbr9.buildprice.security.model.UserDetailsImpl;
import com.tomazbr9.buildprice.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(
            @RequestBody ProjectRequestDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        ProjectResponseDTO response = service.createProject(request, userDetails.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
