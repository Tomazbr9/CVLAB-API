package com.tomazbr9.cvlab.modules.profiles.dto.skillDTO;

import com.tomazbr9.cvlab.modules.profiles.enums.LevelName;
import jakarta.validation.constraints.NotBlank;

public record SkillDTO(

        @NotBlank(message = "Nome da habilidade é obrigatório")
        String skillName,

        String description,

        LevelName level
) {
}
