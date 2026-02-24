package com.tomazbr9.cvlab.modules.profiles.dto.linkDTO;


import com.tomazbr9.cvlab.modules.profiles.enums.SiteName;

import java.util.UUID;

public record LinkResponseDTO(

        UUID id,
        String url,
        SiteName siteName
) {
}
