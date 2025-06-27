package com.bodiva.wisdomacademy.backend.repository;

import com.bodiva.wisdomacademy.backend.model.CoursePurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursePurchaseRepository extends JpaRepository<CoursePurchase, Long> {
    boolean existsByUserWalletAddressAndCourseId(String userWalletAddress, int courseId);
}
