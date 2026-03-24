package com.tomazbr9.cvlab.modules.subscriptions.service;


import com.google.api.services.androidpublisher.model.ProductPurchase;
import com.tomazbr9.cvlab.modules.resumes.entity.Resume;
import com.tomazbr9.cvlab.modules.resumes.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ResumePaymentService {

    @Autowired
    private GooglePlaySubscriptionService googlePlayService;

     @Autowired
     private ResumeRepository resumeRepository;

    @Transactional
    public boolean verifyAndUnlockOptimization(UUID resumeId, String productId, String purchaseToken) {

        // 1. Pede ao Google para validar o recibo da compra única
        ProductPurchase purchase = googlePlayService.verifyInAppProduct(productId, purchaseToken);

        if (purchase == null) {
            return false;
        }

        // 2. Verifica o status da compra no Google
        if (purchase.getPurchaseState() != 0) {
            System.out.println("A compra não foi concluída. Status: " + purchase.getPurchaseState());
            return false;
        }

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Currículo não encontrado"));

        resume.setPaidSingle(true);
        resumeRepository.save(resume);

        return true;
    }
}