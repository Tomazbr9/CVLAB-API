package com.tomazbr9.cvlab.modules.subscriptions.service;

import com.google.api.services.androidpublisher.model.SubscriptionPurchase;
import com.tomazbr9.cvlab.modules.subscriptions.entity.Subscription;
import com.tomazbr9.cvlab.modules.subscriptions.enums.PlanType;
import com.tomazbr9.cvlab.modules.subscriptions.enums.StatusSubscription;
import com.tomazbr9.cvlab.modules.subscriptions.repository.SubscriptionRepository;
import com.tomazbr9.cvlab.modules.users.entity.User;
import com.tomazbr9.cvlab.modules.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class UserSubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GooglePlaySubscriptionService googlePlayService;

    @Transactional
    public boolean verifyAndActivatePremium(UUID userId, String subscriptionId, String purchaseToken) {

        // 1. Pede ao Google para validar o token
        SubscriptionPurchase purchase = googlePlayService.verifySubscription(subscriptionId, purchaseToken);

        // Se o Google devolver nulo, o token é falso ou inválido
        if (purchase == null) {
            return false;
        }

        Integer paymentState = purchase.getPaymentState();
        if (paymentState != null && paymentState == 0) {
            System.out.println("Pagamento ainda está pendente processamento pelo banco/Google.");
            return false;
        }

        // 2. Extrai a data de vencimento que o Google devolveu (vem em milissegundos)
        long expiryTimeMillis = purchase.getExpiryTimeMillis();
        LocalDateTime expiresAt = Instant.ofEpochMilli(expiryTimeMillis)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        // Verifica se a assinatura já venceu no passado
        if (expiresAt.isBefore(LocalDateTime.now())) {
            return false;
        }

        // 3. Busca o usuário e a assinatura atual dele (se não tiver, cria uma nova)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Subscription subscription = subscriptionRepository.findByUserId(userId)
                .orElse(Subscription.builder().user(user).build());

        // 4. Atualiza os dados para PREMIUM
        subscription.setPlanType(PlanType.PREMIUM);
        subscription.setStatus(StatusSubscription.ACTIVE);
        subscription.setGoogleSubscriptionId(subscriptionId);
        subscription.setPurchaseToken(purchaseToken);
        subscription.setExpiresAt(expiresAt);
        subscription.setUpdatedAt(LocalDateTime.now());

        // Salva no banco de dados
        subscriptionRepository.save(subscription);

        return true;
    }
}