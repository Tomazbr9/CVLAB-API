package com.tomazbr9.cvlab.modules.auth.repository;

import com.tomazbr9.cvlab.modules.auth.enums.RoleName;
import com.tomazbr9.cvlab.modules.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(RoleName name);
}
