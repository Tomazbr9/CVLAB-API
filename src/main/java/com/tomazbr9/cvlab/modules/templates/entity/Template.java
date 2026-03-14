package com.tomazbr9.cvlab.modules.templates.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_templates")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Template {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "thumbnail_url", nullable = false)
    private String thumbnailUrl;

    @Column(name = "is_premium", nullable = false)
    private boolean isPremium;
}
