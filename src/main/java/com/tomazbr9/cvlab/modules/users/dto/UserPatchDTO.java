package com.tomazbr9.cvlab.modules.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserPatchDTO(

        @Size(min = 3, max = 50)
        String firstName,

        String lastName,

        @Email(message = "Email inválido")
        String email,

        String password
) {
}
