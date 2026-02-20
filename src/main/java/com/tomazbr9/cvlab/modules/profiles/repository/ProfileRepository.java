package com.tomazbr9.cvlab.modules.profiles.repository;

import com.tomazbr9.cvlab.modules.profiles.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findByIdAndUser_id(UUID profileId, UUID userId);
}
