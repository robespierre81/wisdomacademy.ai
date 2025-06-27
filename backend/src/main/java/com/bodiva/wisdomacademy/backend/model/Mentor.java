package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mentors")
@Getter
@Setter
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String expertise;
    private String bio;
    private String contactEmail;
    private boolean isAvailable; // Availability for mentorship

    // Constructors
    public Mentor() {}
    public Mentor(String name, String expertise, String bio, String contactEmail, boolean isAvailable) {
        this.name = name;
        this.expertise = expertise;
        this.bio = bio;
        this.contactEmail = contactEmail;
        this.isAvailable = isAvailable;
    }
}
