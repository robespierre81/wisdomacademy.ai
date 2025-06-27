package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.model.CourseDetailsView;
import com.bodiva.wisdomacademy.backend.repository.CourseDetailsViewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDetailsViewService {

    private final CourseDetailsViewRepository repository;

    public CourseDetailsViewService(CourseDetailsViewRepository repository) {
        this.repository = repository;
    }

    public List<CourseDetailsView> getCourseDetails(Long courseId, String walletAddress) {
        List<CourseDetailsView> bookedCourses = repository.findByCourseIdAndBookedBy(courseId, walletAddress);
        if (!bookedCourses.isEmpty()) {
            return bookedCourses;
        }
        return repository.findByCourseId(courseId);
    }
}
