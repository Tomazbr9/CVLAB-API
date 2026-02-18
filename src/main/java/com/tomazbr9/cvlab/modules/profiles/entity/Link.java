package com.tomazbr9.cvlab.modules.profiles.entity;

import com.tomazbr9.cvlab.modules.profiles.enums.SiteName;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "tb_profiles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Link {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "site_name", nullable = false)
    private SiteName siteName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;
}
