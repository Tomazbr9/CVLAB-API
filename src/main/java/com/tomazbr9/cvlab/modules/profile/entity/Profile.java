package com.tomazbr9.cvlab.modules.profile.entity;

import com.tomazbr9.cvlab.modules.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
