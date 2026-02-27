package com.tomazbr9.cvlab.modules.resumes.repository;

import com.tomazbr9.cvlab.modules.resumes.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResumeRepository extends JpaRepository<Resume, UUID> {
}
