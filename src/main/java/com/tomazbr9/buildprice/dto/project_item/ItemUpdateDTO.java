package com.tomazbr9.buildprice.dto.project_item;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ItemUpdateDTO(
        Integer quantity
) {
}
