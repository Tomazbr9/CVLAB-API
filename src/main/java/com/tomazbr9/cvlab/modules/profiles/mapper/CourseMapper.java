package com.tomazbr9.cvlab.modules.profiles.mapper;

import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.courseDTO.CourseUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceResponseDTO;
import com.tomazbr9.cvlab.modules.profiles.dto.experienceDTO.ExperienceUpdateDTO;
import com.tomazbr9.cvlab.modules.profiles.entity.Course;
import com.tomazbr9.cvlab.modules.profiles.entity.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {

    Course toEntity(CourseDTO courseDTO);

    List<Course> toEntityList(List<CourseDTO> coursesDTO);

    CourseResponseDTO toDTO(Course entity);

    @Mapping(target = "id", ignore = true)
    void updateDTOFromEntity(CourseUpdateDTO dto, @MappingTarget Course course);
}
