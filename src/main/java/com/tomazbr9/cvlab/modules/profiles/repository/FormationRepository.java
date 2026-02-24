package com.tomazbr9.cvlab.modules.profiles.repository;

import com.tomazbr9.cvlab.modules.profiles.entity.Experience;
import com.tomazbr9.cvlab.modules.profiles.entity.Formation;
import com.tomazbr9.cvlab.modules.profiles.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FormationRepository extends JpaRepository<Formation, UUID> {

    Optional<Formation> findByIdAndProfileIdAndProfileUserId(UUID formationId, UUID profileId, UUID userID);
}
