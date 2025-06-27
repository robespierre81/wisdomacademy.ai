package com.bodiva.wisdomacademy.backend.repository;

import com.bodiva.wisdomacademy.backend.model.Mentor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findByIsAvailable(boolean isAvailable);
}
