package com.tomazbr9.cvlab.modules.profile.repository;

import com.tomazbr9.cvlab.modules.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

}
