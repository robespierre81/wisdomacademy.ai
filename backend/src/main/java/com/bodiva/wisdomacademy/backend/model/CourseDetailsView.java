package com.bodiva.wisdomacademy.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "coursedetailsview")
@Getter
@Setter
public class CourseDetailsView {

    @Id
    @Column(name = "course_id")
    @JsonProperty("course_id")
    private Long courseId;

    @Column(name = "course_title")
    @JsonProperty("course_title")
    private String courseTitle;

    @Column(name = "course_description")
    @JsonProperty("course_description")
    private String courseDescription;

    @Column(name = "course_price")
    @JsonProperty("course_price")
    private Double coursePrice;

    @Column(name = "course_creator")
    @JsonProperty("course_creator")
    private String courseCreator;

    @Column(name = "course_created_at")
    @JsonProperty("course_created_at")
    private LocalDateTime courseCreatedAt;

    @Column(name = "course_updated_at")
    @JsonProperty("course_updated_at")
    private LocalDateTime courseUpdatedAt;

    @Column(name = "course_image")
    @JsonProperty("course_image")
    private String courseImage;

    @Column(name = "booking_id")
    @JsonProperty("booking_id")
    private Long bookingId;

    @Column(name = "booked_by")
    @JsonProperty("booked_by")
    private String bookedBy;

    @Column(name = "booking_status")
    @JsonProperty("booking_status")
    private String bookingStatus;

    @Column(name = "booking_progress")
    @JsonProperty("booking_progress")
    private Double bookingProgress;

    @Column(name = "total_lessons")
    @JsonProperty("total_lessons")
    private Integer totalLessons;

    @Column(name = "completed_lessons")
    @JsonProperty("completed_lessons")
    private Integer completedLessons;

    @Column(name = "completion_percentage")
    @JsonProperty("completion_percentage")
    private Double completionPercentage;

    @Column(name = "lessons", columnDefinition = "jsonb")
    @JsonProperty("lessons")
    @Type(JsonType.class)
    private List<Map<String, Object>> lessons;
}
