package com.bodiva.wisdomacademy.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "badge")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Badge {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String description;
    
    private String iconurl;
    private int points;
    
    @Column(name = "rank")
    private int rank;
}