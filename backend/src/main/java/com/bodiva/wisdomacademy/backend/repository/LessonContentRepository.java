package com.bodiva.wisdomacademy.backend.repository;

import com.bodiva.wisdomacademy.backend.model.LessonContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonContentRepository extends JpaRepository<LessonContent, Long> {}