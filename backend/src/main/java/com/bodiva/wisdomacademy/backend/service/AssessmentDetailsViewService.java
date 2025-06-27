package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.model.AssessmentDetailsView;
import com.bodiva.wisdomacademy.backend.repository.AssessmentDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssessmentDetailsViewService {

    private final AssessmentDetailsViewRepository repository;

    @Autowired
    public AssessmentDetailsViewService(AssessmentDetailsViewRepository repository) {
        this.repository = repository;
    }

    public AssessmentDetailsView getAssessmentDetails(Long id) {
        Optional<AssessmentDetailsView> assessment = repository.findById(id);
        return assessment.orElse(null); // Returns null if not found (Handle better with custom exceptions)
    }
}
