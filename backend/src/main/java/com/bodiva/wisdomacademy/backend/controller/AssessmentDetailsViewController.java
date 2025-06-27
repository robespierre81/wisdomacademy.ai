package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.AssessmentDetailsView;
import com.bodiva.wisdomacademy.backend.service.AssessmentDetailsViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/assessmentdetails")
public class AssessmentDetailsViewController {

    private final AssessmentDetailsViewService service;

    @Autowired
    public AssessmentDetailsViewController(AssessmentDetailsViewService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public AssessmentDetailsView getAssessmentDetails(@PathVariable Long id) {
        return service.getAssessmentDetails(id);
    }
}