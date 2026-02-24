package com.tomazbr9.cvlab.modules.profiles.repository;

import com.tomazbr9.cvlab.modules.profiles.entity.Formation;
import com.tomazbr9.cvlab.modules.profiles.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, UUID> {

    Optional<Skill> findByIdAndProfileIdAndProfileUserId(UUID skillId, UUID profileId, UUID userId);
}
