package com.tomazbr9.cvlab.modules.profiles.repository;

import com.tomazbr9.cvlab.modules.profiles.entity.Experience;
import com.tomazbr9.cvlab.modules.profiles.entity.Project;
import com.tomazbr9.cvlab.modules.profiles.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    Optional<Project> findByIdAndProfileIdAndProfileUserId(UUID projectId, UUID profileId, UUID userId);
}
