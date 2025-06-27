package com.bodiva.wisdomacademy.backend.repository;

import com.bodiva.wisdomacademy.backend.model.AssessmentPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssessmentPurchaseRepository extends JpaRepository<AssessmentPurchase, Long> {
    
    Optional<AssessmentPurchase> findByUserWalletAddressAndAssessmentId(String userWalletAddress, Long assessmentId);
}
