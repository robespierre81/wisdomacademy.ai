package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.model.CourseBookingsView;
import com.bodiva.wisdomacademy.backend.repository.CourseBookingsViewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseBookingsViewService {
    private final CourseBookingsViewRepository repository;

    public CourseBookingsViewService(CourseBookingsViewRepository repository) {
        this.repository = repository;
    }

    // Get all courses booked by the user
    public List<CourseBookingsView> getUserBookings(String walletAddress) {
        return repository.findByBookedBy(walletAddress);
    }

    // Get all courses created by the user
    public List<CourseBookingsView> getUserCreatedCourses(String walletAddress) {
        return repository.findByCourseCreator(walletAddress);
    }

    // Get all completed courses by the user
    public List<CourseBookingsView> getCompletedCourses(String walletAddress) {
        return repository.findByBookedByAndBookingStatus(walletAddress, "COMPLETED");
    }

    // Get all new courses (created in the last two weeks)
    public List<CourseBookingsView> getNewCourses(String walletAddress) {
        LocalDateTime twoWeeksAgo = LocalDateTime.now().minusWeeks(2);
        return repository.findNewCourses(walletAddress, twoWeeksAgo);
    }
}
