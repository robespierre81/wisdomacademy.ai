package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "learning_plans")
@Getter
@Setter
public class LearningPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    
    @Column(name = "category_id")
    private Integer categoryId;
    
    @Column(name = "createdby")  // assuming your DB column is lowercase
    private String createdBy;

    @ManyToMany
    @JoinTable(
        name = "learning_plan_courses",
        joinColumns = @JoinColumn(name = "learning_plan_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    @ManyToMany
    @JoinTable(
        name = "learning_plan_assessments",
        joinColumns = @JoinColumn(name = "learning_plan_id"),
        inverseJoinColumns = @JoinColumn(name = "assessment_id")
    )
    private List<Assessment> assessments;

    @ManyToMany
    @JoinTable(
        name = "learning_plan_badges",
        joinColumns = @JoinColumn(name = "learning_plan_id"),
        inverseJoinColumns = @JoinColumn(name = "badge_id")
    )
    private List<Badge> badges;

}