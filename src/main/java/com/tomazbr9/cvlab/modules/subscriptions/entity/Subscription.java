package com.tomazbr9.cvlab.modules.subscriptions.entity;

import com.tomazbr9.cvlab.modules.subscriptions.enums.PlanType;
import com.tomazbr9.cvlab.modules.subscriptions.enums.StatusSubscription;
import com.tomazbr9.cvlab.modules.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_subscription")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Subscription {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan_type", nullable = false)
    private PlanType planType = PlanType.FREE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSubscription status = StatusSubscription.ACTIVE;

    @Column(name = "google_subscription_id")
    private String googleSubscriptionId;

    @Column(name = "purchase_token", length = 1000)
    private String purchaseToken;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

}