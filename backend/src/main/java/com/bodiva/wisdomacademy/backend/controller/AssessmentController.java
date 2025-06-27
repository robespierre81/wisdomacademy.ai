package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.Assessment;
import com.bodiva.wisdomacademy.backend.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {
    
    @Autowired
    private AssessmentRepository assessmentRepository;

    @GetMapping
    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    @PostMapping
    public Assessment createAssessment(@RequestBody Assessment assessment) {
        return assessmentRepository.save(assessment);
    }
}
