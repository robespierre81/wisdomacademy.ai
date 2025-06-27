package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.dto.LearningPlanRequest;
import com.bodiva.wisdomacademy.backend.model.*;
import com.bodiva.wisdomacademy.backend.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LearningPlanService {
    private final LearningPlanRepository learningPlanRepository;
    private final CourseRepository courseRepository;
    private final AssessmentRepository assessmentRepository;
    private final BadgeRepository badgeRepository;
    private final CategoryRepository categoryRepository;

    
    public LearningPlan createLearningPlan(LearningPlanRequest request) {
        LearningPlan plan = new LearningPlan();
        populateLearningPlan(plan, request);
        return learningPlanRepository.save(plan);
    }

    public LearningPlan updateLearningPlan(Long id, LearningPlanRequest request) {
        LearningPlan plan = learningPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Learning plan not found"));
        populateLearningPlan(plan, request);
        return learningPlanRepository.save(plan);
    }

    private void populateLearningPlan(LearningPlan plan, LearningPlanRequest request) {
        plan.setTitle(request.getTitle());
        plan.setDescription(request.getDescription());
        plan.setCreatedBy(request.getCreatedBy());

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            plan.setCategoryId(request.getCategoryId());
        } else {
            plan.setCategoryId(-1);
        }

        plan.setCourses(courseRepository.findAllById(request.getCourseIds()));
        plan.setAssessments(assessmentRepository.findAllById(request.getAssessmentIds()));
        plan.setBadges(badgeRepository.findAllById(request.getBadgeIds()));
    }

    public List<LearningPlan> findByCreatedBy(String walletAddress) {
        return learningPlanRepository.findByCreatedBy(walletAddress);
    }

    public List<LearningPlan> getAllLearningPlans() {
        return learningPlanRepository.findAll();
    }

    public Optional<LearningPlan> getLearningPlanById(Long id) {
        return learningPlanRepository.findById(id);
    }

    public List<LearningPlan> getLearningPlansByUserId(String userId) {
        return learningPlanRepository.findByCreatedBy(userId);
    }

    public LearningPlan saveLearningPlan(LearningPlan learningPlan) {
        return learningPlanRepository.save(learningPlan);
    }

    public boolean deleteLearningPlan(Long id) {
        if (learningPlanRepository.existsById(id)) {
            learningPlanRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<LearningPlan> addCourseToPlan(Long planId, Long courseId) {
        Optional<LearningPlan> planOpt = learningPlanRepository.findById(planId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (planOpt.isPresent() && courseOpt.isPresent()) {
            LearningPlan plan = planOpt.get();
            plan.getCourses().add(courseOpt.get());
            return Optional.of(learningPlanRepository.save(plan));
        }

        return Optional.empty();
    }

    public Optional<LearningPlan> removeCourseFromPlan(Long planId, Long courseId) {
        Optional<LearningPlan> planOpt = learningPlanRepository.findById(planId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (planOpt.isPresent() && courseOpt.isPresent()) {
            LearningPlan plan = planOpt.get();
            plan.getCourses().remove(courseOpt.get());
            return Optional.of(learningPlanRepository.save(plan));
        }

        return Optional.empty();
    }

    public Optional<LearningPlan> addAssessmentToPlan(Long planId, Long assessmentId) {
        Optional<LearningPlan> planOpt = learningPlanRepository.findById(planId);
        Optional<Assessment> assessmentOpt = assessmentRepository.findById(assessmentId);

        if (planOpt.isPresent() && assessmentOpt.isPresent()) {
            LearningPlan plan = planOpt.get();
            plan.getAssessments().add(assessmentOpt.get());
            return Optional.of(learningPlanRepository.save(plan));
        }

        return Optional.empty();
    }

    public Optional<LearningPlan> removeAssessmentFromPlan(Long planId, Long assessmentId) {
        Optional<LearningPlan> planOpt = learningPlanRepository.findById(planId);
        Optional<Assessment> assessmentOpt = assessmentRepository.findById(assessmentId);

        if (planOpt.isPresent() && assessmentOpt.isPresent()) {
            LearningPlan plan = planOpt.get();
            plan.getAssessments().remove(assessmentOpt.get());
            return Optional.of(learningPlanRepository.save(plan));
        }

        return Optional.empty();
    }

    public Optional<LearningPlan> addBadgeToPlan(Long planId, Long badgeId) {
        Optional<LearningPlan> planOpt = learningPlanRepository.findById(planId);
        Optional<Badge> badgeOpt = badgeRepository.findById(badgeId);

        if (planOpt.isPresent() && badgeOpt.isPresent()) {
            LearningPlan plan = planOpt.get();
            plan.getBadges().add(badgeOpt.get());
            return Optional.of(learningPlanRepository.save(plan));
        }

        return Optional.empty();
    }

    public Optional<LearningPlan> removeBadgeFromPlan(Long planId, Long badgeId) {
        Optional<LearningPlan> planOpt = learningPlanRepository.findById(planId);
        Optional<Badge> badgeOpt = badgeRepository.findById(badgeId);

        if (planOpt.isPresent() && badgeOpt.isPresent()) {
            LearningPlan plan = planOpt.get();
            plan.getBadges().remove(badgeOpt.get());
            return Optional.of(learningPlanRepository.save(plan));
        }

        return Optional.empty();
    }
}
