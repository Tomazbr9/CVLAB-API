package com.tomazbr9.cvlab.modules.subscriptions.controller;

import com.tomazbr9.cvlab.modules.subscriptions.dto.SubscriptionVerifyRequest;
import com.tomazbr9.cvlab.modules.subscriptions.service.UserSubscriptionService;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    @Autowired
    private UserSubscriptionService userSubscriptionService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPurchase(
            @RequestBody SubscriptionVerifyRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {

        boolean isPremiumAtivado = userSubscriptionService.verifyAndActivatePremium(
                userDetails.getId(),
                request.subscriptionId(),
                request.purchaseToken()
        );

        if (isPremiumAtivado) {
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Assinatura ativada com sucesso! Bem-vindo ao Premium."
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Token inválido, expirado ou não reconhecido pelo Google."
            ));
        }
    }
}