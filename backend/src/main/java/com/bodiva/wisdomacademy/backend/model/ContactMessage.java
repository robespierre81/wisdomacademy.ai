package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "contact_message")
@Data
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String subject;
    
    @Column(columnDefinition = "TEXT")
    private String message;
}
