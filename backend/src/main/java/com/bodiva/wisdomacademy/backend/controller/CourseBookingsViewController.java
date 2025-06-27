package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.CourseBookingsView;
import com.bodiva.wisdomacademy.backend.service.CourseBookingsViewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class CourseBookingsViewController {
    private final CourseBookingsViewService service;

    public CourseBookingsViewController(CourseBookingsViewService service) {
        this.service = service;
    }

    @GetMapping("/user/{walletAddress}")
    public List<CourseBookingsView> getUserBookings(@PathVariable String walletAddress) {
        return service.getUserBookings(walletAddress);
    }

    @GetMapping("/created/{walletAddress}")
    public List<CourseBookingsView> getUserCreatedCourses(@PathVariable String walletAddress) {
        return service.getUserCreatedCourses(walletAddress);
    }

    @GetMapping("/completed/{walletAddress}")
    public List<CourseBookingsView> getCompletedCourses(@PathVariable String walletAddress) {
        return service.getCompletedCourses(walletAddress);
    }

    @GetMapping("/new/{walletAddress}")
    public List<CourseBookingsView> getNewCourses(@PathVariable String walletAddress) {
        return service.getNewCourses(walletAddress);
    }
}
