package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "assessment_purchases", uniqueConstraints = @UniqueConstraint(columnNames = {"user_wallet_address", "assessment_id"}))
@Getter
@Setter
public class AssessmentPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userWalletAddress;
    
    @ManyToOne
    @JoinColumn(name = "assessment_id", nullable = false)
    private Assessment assessment;
    
    private LocalDateTime purchaseDate;
    
    private BigDecimal lastScore; // Percentage score of the last attempt
    
    private int attempts;
    
    private String status; // not_started, in_progress, completed, failed

    // Constructors
    public AssessmentPurchase() {}

    public AssessmentPurchase(String userWalletAddress, Assessment assessment) {
        this.userWalletAddress = userWalletAddress;
        this.assessment = assessment;
        this.purchaseDate = LocalDateTime.now();
        this.status = "not_started";
        this.attempts = 0;
        this.lastScore = null;
    }
}
