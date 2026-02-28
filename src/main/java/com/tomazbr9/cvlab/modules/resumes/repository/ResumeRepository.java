package com.tomazbr9.cvlab.modules.resumes.repository;

import com.tomazbr9.cvlab.modules.resumes.entity.Resume;
import com.tomazbr9.cvlab.modules.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResumeRepository extends JpaRepository<Resume, UUID> {

    Optional<Resume> findByIdAndUser(UUID resumeId, User user);

    List<Resume> findByUser(User user);
}
