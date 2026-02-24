package com.tomazbr9.cvlab.modules.profiles.repository;

import com.tomazbr9.cvlab.modules.profiles.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ExperienceRepository extends JpaRepository<Experience, UUID> {

    Optional<Experience> findByIdAndProfileIdAndProfileUserId(UUID experienceId, UUID profileId, UUID userID);
}
