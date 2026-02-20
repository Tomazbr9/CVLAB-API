package com.tomazbr9.cvlab.modules.profiles.entity;

import com.tomazbr9.cvlab.modules.profiles.enums.FormationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_formations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Formation {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "institution_name", nullable = false)
    private String institutionName;

    @Column(name = "course_location")
    private String courseLocation;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "formation_status", nullable = false)
    private FormationStatus formationStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

}
