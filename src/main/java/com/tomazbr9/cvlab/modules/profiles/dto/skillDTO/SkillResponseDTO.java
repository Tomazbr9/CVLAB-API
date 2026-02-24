package com.tomazbr9.cvlab.modules.profiles.dto.skillDTO;

import com.tomazbr9.cvlab.modules.profiles.enums.LevelName;

import java.util.UUID;

public record SkillResponseDTO(

        UUID id,

        String skillName,

        String description,

        LevelName level
) {}
