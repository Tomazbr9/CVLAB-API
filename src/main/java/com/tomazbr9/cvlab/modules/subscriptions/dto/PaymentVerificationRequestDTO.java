package com.tomazbr9.cvlab.modules.subscriptions.dto;

import java.util.UUID;

public record PaymentVerificationRequestDTO(
        UUID resumeId,
        String productId,
        String purchaseToken
) {
}
