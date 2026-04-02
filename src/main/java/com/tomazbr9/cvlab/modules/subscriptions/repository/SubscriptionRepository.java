package com.tomazbr9.cvlab.modules.subscriptions.repository;

import com.tomazbr9.cvlab.modules.subscriptions.entity.Subscription;
import com.tomazbr9.cvlab.modules.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    Optional<Subscription> findByUser(User user);
}
