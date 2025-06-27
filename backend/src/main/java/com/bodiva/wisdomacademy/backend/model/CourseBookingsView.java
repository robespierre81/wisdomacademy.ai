package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "coursebookingsview")
@Getter
@Setter
public class CourseBookingsView {

    @Id
    @JsonProperty("courseid")
    @Column(name = "courseid")
    private Long courseId;

    @JsonProperty("coursetitle")
    @Column(name = "coursetitle")
    private String courseTitle;

    @JsonProperty("coursedescription")
    @Column(name = "coursedescription")
    private String courseDescription;

    @JsonProperty("courseprice")
    @Column(name = "courseprice")
    private Double coursePrice;

    @JsonProperty("coursecreator")
    @Column(name = "coursecreator")
    private String courseCreator;

    @JsonProperty("coursecreatedat")
    @Column(name = "coursecreatedat") // ✅ Ensure exact match with view column
    private LocalDateTime courseCreatedAt;

    @JsonProperty("courseupdatedat")
    @Column(name = "courseupdatedat")
    private LocalDateTime courseUpdatedAt;

    @JsonProperty("courseimage")
    @Column(name = "courseimage")
    private String courseImage;

    @JsonProperty("bookingid")
    @Column(name = "bookingid")
    private Long bookingId;

    @JsonProperty("bookedby")
    @Column(name = "bookedby")
    private String bookedBy;

    @JsonProperty("bookingstatus")
    @Column(name = "bookingstatus")
    private String bookingStatus;

    @JsonProperty("bookingprogress")
    @Column(name = "bookingprogress")
    private Double bookingProgress;

    @JsonProperty("enrolledat")
    @Column(name = "enrolledat")
    private LocalDateTime enrolledAt;

    @JsonProperty("bookingupdatedat")
    @Column(name = "bookingupdatedat")
    private LocalDateTime bookingUpdatedAt;

    // ✅ Constructor
    public CourseBookingsView() {}
}
