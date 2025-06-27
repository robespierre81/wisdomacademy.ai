package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.service.AssessmentPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/assessment-purchases")
public class AssessmentPurchaseController {

    @Autowired
    private AssessmentPurchaseService purchaseService;

    @PostMapping("/buy/{assessmentId}")
    public String buyAssessment(@PathVariable Long assessmentId, @RequestParam String walletAddress) {
        return purchaseService.buyAssessment(walletAddress, assessmentId);
    }

    @PostMapping("/attempt/{assessmentId}")
    public String recordAttempt(@PathVariable Long assessmentId, @RequestBody Map<String, Object> payload) {
        String walletAddress = (String) payload.get("walletAddress");
        BigDecimal score = new BigDecimal(payload.get("score").toString());

        return purchaseService.recordAttempt(walletAddress, assessmentId, score);
    }
}
