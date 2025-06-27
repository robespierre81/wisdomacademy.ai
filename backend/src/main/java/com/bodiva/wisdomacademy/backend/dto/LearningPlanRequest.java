package com.bodiva.wisdomacademy.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class LearningPlanRequest {
    private String title;
    private String description;
    private String createdBy;
    private Integer categoryId;

    private List<Long> courseIds;
    private List<Long> assessmentIds;
    private List<Long> badgeIds;
}
