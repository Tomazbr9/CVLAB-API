package com.tomazbr9.cvlab.modules.profiles.dto.skillDTO;

import com.tomazbr9.cvlab.modules.profiles.enums.LevelName;
import jakarta.validation.constraints.NotBlank;

public record SkillUpdateDTO(

        String skillName,

        String description,

        LevelName level
) {}
