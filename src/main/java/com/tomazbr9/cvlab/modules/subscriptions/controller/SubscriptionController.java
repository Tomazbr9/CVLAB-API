package com.tomazbr9.cvlab.modules.subscriptions.controller;

import com.tomazbr9.cvlab.modules.subscriptions.dto.PaymentVerificationRequestDTO;
import com.tomazbr9.cvlab.modules.subscriptions.dto.SubscriptionResponseDTO;
import com.tomazbr9.cvlab.modules.subscriptions.service.ResumePaymentService;
import com.tomazbr9.cvlab.modules.subscriptions.service.UserSubscriptionService;
import com.tomazbr9.cvlab.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    @Autowired
    private UserSubscriptionService userSubscriptionService;

    @Autowired
    private ResumePaymentService resumePaymentService;

    @GetMapping
    public ResponseEntity<SubscriptionResponseDTO> getSubscription(@AuthenticationPrincipal UserDetailsImpl userDetails){

        SubscriptionResponseDTO response = userSubscriptionService.getSubscription(userDetails.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPurchase(
            @RequestBody PaymentVerificationRequestDTO request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {

        boolean isPremiumAtivado = userSubscriptionService.verifyAndActivatePremium(
                userDetails.getId(),
                request.productId(),
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

    @PostMapping("/verify-single")
    public ResponseEntity<Object> verifySinglePurchase(@RequestBody PaymentVerificationRequestDTO request) {

        // 1. Validação básica de segurança para garantir que o app mandou tudo
        if (request.purchaseToken() == null || request.resumeId() == null || request.productId() == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Dados de pagamento incompletos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        // 2. Chama o serviço que vai até os servidores do Google validar o recibo
        boolean isPaymentValid = resumePaymentService.verifyAndUnlockOptimization(
                request.resumeId(),
                request.productId(),
                request.purchaseToken()
        );

        if (isPaymentValid) {
            Map<String, String> success = new HashMap<>();
            success.put("message", "Pagamento validado com sucesso. Otimização com IA liberada!");
            // Retorna 200 OK
            return ResponseEntity.ok(success);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Não foi possível validar o pagamento junto ao Google. Token inválido ou compra não concluída.");
            // Retorna 400 Bad Request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}