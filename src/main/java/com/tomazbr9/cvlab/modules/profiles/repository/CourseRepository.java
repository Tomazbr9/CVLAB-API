package com.tomazbr9.cvlab.modules.profiles.repository;

import com.tomazbr9.cvlab.modules.profiles.entity.Course;
import com.tomazbr9.cvlab.modules.profiles.entity.Experience;
import com.tomazbr9.cvlab.modules.profiles.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {

    Optional<Course> findByIdAndProfileIdAndProfileUserId(UUID courseId, UUID profileId, UUID userID);

}
