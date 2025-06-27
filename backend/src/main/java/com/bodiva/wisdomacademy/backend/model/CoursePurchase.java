package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "course_purchases", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_wallet_address", "course_id"})
})
public class CoursePurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_wallet_address", length = 42, nullable = false)
    private String userWalletAddress;

    @Column(name = "course_id", nullable = false)
    private int courseId;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate = LocalDateTime.now();

    @Column(name = "progress")
    private Integer progress = 0;

    @Column(name = "status")
    private String status = "not_started";
}
