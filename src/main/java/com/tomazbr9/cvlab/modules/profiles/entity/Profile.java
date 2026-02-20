package com.tomazbr9.cvlab.modules.profiles.entity;

import com.tomazbr9.cvlab.modules.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_profiles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Profile {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    private String phone;

    @Column(name = "professional_summary", nullable = false, columnDefinition = "TEXT")
    private String professionalSummary;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
    private List<Formation> formations;

    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
    private List<Skill> skills;

    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
    private List<Project> projects;

    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
    private List<Link> links;

    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
    private List<Course> courses;

}
