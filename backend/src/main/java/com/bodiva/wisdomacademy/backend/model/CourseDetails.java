package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "coursedetailsview")
@Getter
@Setter
public class CourseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("duration")
    private String duration;

    @Enumerated(EnumType.STRING)
    @JsonProperty("language")
    private Language language;

    @Enumerated(EnumType.STRING)
    @JsonProperty("difficulty")
    private Difficulty difficulty;

    @JsonProperty("instructor")
    private String instructor;

    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("syllabus")
    @Column(columnDefinition = "TEXT")
    private String syllabus; // JSON formatted string

    @JsonProperty("prerequisites")
    @Column(columnDefinition = "TEXT")
    private String prerequisites; // JSON formatted string

    // Constructors
    public CourseDetails() {}

    public CourseDetails(Course course, String imageUrl, String duration, Language language, Difficulty difficulty,
                         String instructor, Double rating, String syllabus, String prerequisites) {
        this.course = course;
        this.imageUrl = imageUrl;
        this.duration = duration;
        this.language = language;
        this.difficulty = difficulty;
        this.instructor = instructor;
        this.rating = rating;
        this.syllabus = syllabus;
        this.prerequisites = prerequisites;
    }
}
