package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "userlessonsview")  
@Getter
@Setter
public class UserLessonsView {

    @Id
    @Column(name = "lesson_id")  
    @JsonProperty("lesson_id")
    private Long lessonId;

    @Column(name = "booked_by")
    @JsonProperty("booked_by")
    private String bookedBy;

    @Column(name = "course_id")
    @JsonProperty("course_id")
    private Long courseId;

    @Column(name = "course_title")
    @JsonProperty("course_title")
    private String courseTitle;

    @Column(name = "lesson_title")
    @JsonProperty("lesson_title")
    private String lessonTitle;

    @Column(name = "lesson_type")
    @JsonProperty("lesson_type")
    private String lessonType;

    @Column(name = "lesson_duration")
    @JsonProperty("lesson_duration")
    private Integer lessonDuration;

    @Column(name = "lesson_status")
    @JsonProperty("lesson_status")
    private String lessonStatus;

    @Column(name = "lesson_created_at")
    @JsonProperty("lesson_created_at")
    private LocalDateTime lessonCreatedAt;

    // Getters & Setters
}
