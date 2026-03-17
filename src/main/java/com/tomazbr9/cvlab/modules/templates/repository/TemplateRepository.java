package com.tomazbr9.cvlab.modules.templates.repository;

import com.tomazbr9.cvlab.modules.templates.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TemplateRepository extends JpaRepository<Template, UUID> {
}
