package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.repository.CourseRepository;

import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
}
