package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"user\"", nullable = false)
    private String userId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("createdat")
    private LocalDateTime createdAt;

    @JsonProperty("updatedat")
    private LocalDateTime updatedAt;

    @JsonProperty("imageurl")
    private String imageUrl;

    @JsonProperty("walletaddress")
    private String walletAddress; // Identifies the creator or "PLATFORM" if system-generated

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
        name = "course_assessments",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "assessment_id")
    )
    private List<Assessment> assessments;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Course() {
    }
}
