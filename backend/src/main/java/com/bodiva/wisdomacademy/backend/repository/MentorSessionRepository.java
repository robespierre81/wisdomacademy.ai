package com.bodiva.wisdomacademy.backend.repository;

import com.bodiva.wisdomacademy.backend.model.MentorSession;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorSessionRepository extends JpaRepository<MentorSession, Long> {
    List<MentorSession> findByUserId(Long userId);
    List<MentorSession> findByMentorId(Long mentorId);
}