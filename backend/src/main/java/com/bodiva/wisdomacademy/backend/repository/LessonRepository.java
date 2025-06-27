package com.bodiva.wisdomacademy.backend.repository;

import com.bodiva.wisdomacademy.backend.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author bodi
 */
    public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
