package com.bodiva.wisdomacademy.backend.repository;

import com.bodiva.wisdomacademy.backend.model.Course;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCreatedAtAfter(LocalDateTime timestamp);

    List<Course> findByWalletAddress(String walletAddress);

    List<Course> findByUserId(String userId);

    List<Course> findByCategoryId(Long categoryId);
}
