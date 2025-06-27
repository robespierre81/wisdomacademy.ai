package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mentor_sessions")
@Getter
@Setter
public class MentorSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long mentorId;
    private Long userId;
    private LocalDateTime sessionTime;
    private boolean isConfirmed;

    // Constructors
    public MentorSession() {}
    public MentorSession(Long mentorId, Long userId, LocalDateTime sessionTime, boolean isConfirmed) {
        this.mentorId = mentorId;
        this.userId = userId;
        this.sessionTime = sessionTime;
        this.isConfirmed = isConfirmed;
    }
}
