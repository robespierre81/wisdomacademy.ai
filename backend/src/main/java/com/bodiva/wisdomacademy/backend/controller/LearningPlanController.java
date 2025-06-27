package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.LearningPlan;
import com.bodiva.wisdomacademy.backend.service.LearningPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/learningplans")
public class LearningPlanController {
    private final LearningPlanService service;

    public LearningPlanController(LearningPlanService service) {
        this.service = service;
    }

    @GetMapping
    public List<LearningPlan> getAllLearningPlans() {
        return service.getAllLearningPlans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearningPlan> getLearningPlanById(@PathVariable Long id) {
        Optional<LearningPlan> learningPlan = service.getLearningPlanById(id);
        return learningPlan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<LearningPlan> getLearningPlansByUser(@PathVariable String userId) {
        return service.getLearningPlansByUserId(userId);
    }

    @PostMapping
    public LearningPlan createLearningPlan(@RequestBody LearningPlan learningPlan) {
        return service.saveLearningPlan(learningPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearningPlan> updateLearningPlan(@PathVariable Long id, @RequestBody LearningPlan updatedPlan) {
        Optional<LearningPlan> existing = service.getLearningPlanById(id);
        if (existing.isPresent()) {
            updatedPlan.setId(id);
            return ResponseEntity.ok(service.saveLearningPlan(updatedPlan));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearningPlan(@PathVariable Long id) {
        boolean deleted = service.deleteLearningPlan(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/add-course/{courseId}")
    public ResponseEntity<LearningPlan> addCourse(@PathVariable Long id, @PathVariable Long courseId) {
        return ResponseEntity.of(service.addCourseToPlan(id, courseId));
    }

    @PostMapping("/{id}/add-assessment/{assessmentId}")
    public ResponseEntity<LearningPlan> addAssessment(@PathVariable Long id, @PathVariable Long assessmentId) {
        return ResponseEntity.of(service.addAssessmentToPlan(id, assessmentId));
    }

    @PostMapping("/{id}/add-badge/{badgeId}")
    public ResponseEntity<LearningPlan> addBadge(@PathVariable Long id, @PathVariable Long badgeId) {
        return ResponseEntity.of(service.addBadgeToPlan(id, badgeId));
    }

    @DeleteMapping("/{id}/remove-course/{courseId}")
    public ResponseEntity<LearningPlan> removeCourse(@PathVariable Long id, @PathVariable Long courseId) {
        return ResponseEntity.of(service.removeCourseFromPlan(id, courseId));
    }

    @DeleteMapping("/{id}/remove-assessment/{assessmentId}")
    public ResponseEntity<LearningPlan> removeAssessment(@PathVariable Long id, @PathVariable Long assessmentId) {
        return ResponseEntity.of(service.removeAssessmentFromPlan(id, assessmentId));
    }

    @DeleteMapping("/{id}/remove-badge/{badgeId}")
    public ResponseEntity<LearningPlan> removeBadge(@PathVariable Long id, @PathVariable Long badgeId) {
        return ResponseEntity.of(service.removeBadgeFromPlan(id, badgeId));
    }
}
