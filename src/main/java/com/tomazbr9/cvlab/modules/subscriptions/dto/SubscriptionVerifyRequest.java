package com.tomazbr9.cvlab.modules.subscriptions.dto;

public record SubscriptionVerifyRequest(
        String subscriptionId,
        String purchaseToken
) {
}
