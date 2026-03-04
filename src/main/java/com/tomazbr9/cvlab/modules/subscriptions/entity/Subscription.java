package com.tomazbr9.cvlab.modules.subscriptions.entity;

import com.tomazbr9.cvlab.modules.subscriptions.enums.StatusSubscription;
import com.tomazbr9.cvlab.modules.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

//@Entity
//@Table(name = "tb_subscription")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Builder
//public class Subscription {
//
//    @Id
//    @GeneratedValue
//    @Column(nullable = false, updatable = false)
//    private UUID id;
//
//    private StatusSubscription status;
//
//    @Column(name = "current_period_start", nullable = false)
//    private Instant currentPeriodStart;
//
//    @Column(name = "current_period_end", nullable = false)
//    private Instant currentPeriodEnd;
//
//    @Column(name = "created_at", nullable = false)
//    private Instant createdAt;
//
//    @Column(name = "updated_at", nullable = false)
//    private Instant updatedAt;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//}
