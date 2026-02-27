package com.tomazbr9.cvlab.modules.resumes.entity;

import com.tomazbr9.cvlab.modules.resumes.dto.ResumeDTO;
import com.tomazbr9.cvlab.modules.users.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_resumes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Resume {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(name = "job_description", columnDefinition = "TEXT")
    private String jobDescription;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "optimized_json", columnDefinition = "JSONB")
    private ResumeDTO optimizedJson;

    @Column(name = "is_paid_single")
    private boolean isPaidSingle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "template_id", nullable = false)
//    private String templateId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "update_at", nullable = false)
    private Instant updateAt;

}
