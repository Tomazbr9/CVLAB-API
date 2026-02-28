package com.tomazbr9.cvlab.modules.resumes.mapper;

import com.tomazbr9.cvlab.modules.resumes.dto.ResumeResponseDTO;
import com.tomazbr9.cvlab.modules.resumes.entity.Resume;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResumeMapper {

    ResumeResponseDTO toDTO(Resume entity);

    List<ResumeResponseDTO> toDTOList(List<Resume> entityList);

}
