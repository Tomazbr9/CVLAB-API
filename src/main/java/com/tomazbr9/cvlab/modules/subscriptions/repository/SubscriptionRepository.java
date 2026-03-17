package com.tomazbr9.cvlab.modules.subscriptions.repository;

import com.tomazbr9.cvlab.modules.subscriptions.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    Optional<Subscription> findByUserId(UUID userId);
}
