package com.bodiva.wisdomacademy.backend.repository;

import com.bodiva.wisdomacademy.backend.model.CourseDetailsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDetailsViewRepository extends JpaRepository<CourseDetailsView, Long> {
    List<CourseDetailsView> findByCourseId(Long courseId);
    List<CourseDetailsView> findByCourseIdAndBookedBy(Long courseId, String bookedBy);
}
