package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.CourseDetailsView;
import com.bodiva.wisdomacademy.backend.service.CourseDetailsViewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coursedetails")
public class CourseDetailsViewController {

    private final CourseDetailsViewService service;

    public CourseDetailsViewController(CourseDetailsViewService service) {
        this.service = service;
    }

    @GetMapping("/{courseId}")
    public List<CourseDetailsView> getCourseDetails(@PathVariable Long courseId, @RequestParam(required = false) String walletAddress) {
        return service.getCourseDetails(courseId, walletAddress);
    }
}
