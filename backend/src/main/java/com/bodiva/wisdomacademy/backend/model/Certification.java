package com.bodiva.wisdomacademy.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "certifications")
@Getter
@Setter
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("userid")
    private String userId; // Blockchain User ID
    
    @JsonProperty("coursetitle")
    private String courseTitle;
    
    private String description;
    
    @JsonProperty("imageurl")
    private String imageUrl;

    // Constructors
    public Certification() {}
    
    public Certification(String courseTitle, String description, String imageUrl, String userId) {
        this.courseTitle = courseTitle;
        this.description = description;
        this.imageUrl = imageUrl;
        this.userId = userId;
    }
}
