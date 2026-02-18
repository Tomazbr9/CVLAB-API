package com.tomazbr9.cvlab.modules.profiles.entity;

import com.tomazbr9.cvlab.modules.profiles.enums.LevelName;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_skills")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Skill {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(name = "skill_name", nullable = false)
    private String skillName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private LevelName level;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;
}
