package com.tomazbr9.buildprice.service;

import com.tomazbr9.buildprice.dto.project.ProjectRequestDTO;
import com.tomazbr9.buildprice.dto.project.ProjectResponseDTO;
import com.tomazbr9.buildprice.entity.Project;
import com.tomazbr9.buildprice.entity.User;
import com.tomazbr9.buildprice.repository.ProjectRepository;
import com.tomazbr9.buildprice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository){
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public ProjectResponseDTO createProject(ProjectRequestDTO request, UUID userId){

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Project newProject = new Project(null, request.nameWork(), Instant.now(), request.bdi(), user);

        Project savedProject = projectRepository.save(newProject);

        return new ProjectResponseDTO(savedProject.getId(), savedProject.getNameWork(), savedProject.getBdi(), savedProject.getCreatedAt());

    }


}
