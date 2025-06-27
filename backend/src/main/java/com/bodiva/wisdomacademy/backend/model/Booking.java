package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_wallet_address", nullable = false)
    private String userWalletAddress;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "progress", columnDefinition = "NUMERIC(5,2) DEFAULT 0.00")
    private Double progress = 0.0;

    @Column(name = "enrolled_at", nullable = false)
    private LocalDateTime enrolledAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
}
