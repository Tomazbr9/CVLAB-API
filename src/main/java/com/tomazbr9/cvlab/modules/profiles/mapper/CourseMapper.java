package com.tomazbr9.cvlab.modules.profiles.mapper;

import com.tomazbr9.cvlab.modules.profiles.dto.CourseDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course toEntity(CourseDTO courseDTO);

    List<Course> toEntityList(List<CourseDTO> coursesDTO);
}
