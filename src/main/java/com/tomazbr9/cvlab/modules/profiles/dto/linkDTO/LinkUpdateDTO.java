package com.tomazbr9.cvlab.modules.profiles.dto.linkDTO;


import com.tomazbr9.cvlab.modules.profiles.enums.SiteName;

public record LinkUpdateDTO(

        String url,
        SiteName siteName
) {
}
