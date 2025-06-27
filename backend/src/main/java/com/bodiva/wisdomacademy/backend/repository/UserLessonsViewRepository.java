package com.bodiva.wisdomacademy.backend.repository;

import com.bodiva.wisdomacademy.backend.model.UserLessonsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserLessonsViewRepository extends JpaRepository<UserLessonsView, Long> {
    List<UserLessonsView> findByBookedBy(String walletAddress);
}
