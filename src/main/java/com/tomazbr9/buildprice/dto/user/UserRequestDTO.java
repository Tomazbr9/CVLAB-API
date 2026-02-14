package com.tomazbr9.buildprice.dto.user;

import com.tomazbr9.buildprice.enums.RoleName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 50)
        String firstName,

        String lastName,

        @NotBlank(message = "Por favor, digite um email válido")
        String email,

        @NotBlank(message = "Por favor, digite uma senha válida")
        String password
) {}
