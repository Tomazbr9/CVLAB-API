package com.tomazbr9.cvlab.modules.subscriptions.dto;

import com.tomazbr9.cvlab.modules.subscriptions.enums.PlanType;

public record SubscriptionResponseDTO(
        PlanType planType
) {
}
