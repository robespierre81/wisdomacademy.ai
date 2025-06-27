package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.model.Assessment;
import com.bodiva.wisdomacademy.backend.model.AssessmentPurchase;
import com.bodiva.wisdomacademy.backend.repository.AssessmentPurchaseRepository;
import com.bodiva.wisdomacademy.backend.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AssessmentPurchaseService {

    @Autowired
    private AssessmentPurchaseRepository purchaseRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    public String buyAssessment(String userWalletAddress, Long assessmentId) {
        if (purchaseRepository.findByUserWalletAddressAndAssessmentId(userWalletAddress, assessmentId).isPresent()) {
            return "Assessment already purchased.";
        }

        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        AssessmentPurchase purchase = new AssessmentPurchase(userWalletAddress, assessment);
        purchaseRepository.save(purchase);

        return "Assessment purchased successfully!";
    }

    public String recordAttempt(String userWalletAddress, Long assessmentId, BigDecimal score) {
        AssessmentPurchase purchase = purchaseRepository.findByUserWalletAddressAndAssessmentId(userWalletAddress, assessmentId)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));

        int newAttempts = purchase.getAttempts() + 1;
        purchase.setAttempts(newAttempts);
        purchase.setLastScore(score);

        if (score.compareTo(BigDecimal.valueOf(50)) >= 0) { // Pass threshold: 50%
            purchase.setStatus("completed");
        } else if (newAttempts >= 3) {
            purchase.setStatus("failed");
        } else {
            purchase.setStatus("in_progress");
        }

        purchaseRepository.save(purchase);
        return "Attempt recorded.";
    }
}
