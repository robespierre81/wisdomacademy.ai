package com.bodiva.wisdomacademy.backend.dto;

import java.util.List;
import lombok.Data;

@Data
public class AssessmentRequest {
    private String title;
    private String description;
    private List<QuestionRequest> questions;
}