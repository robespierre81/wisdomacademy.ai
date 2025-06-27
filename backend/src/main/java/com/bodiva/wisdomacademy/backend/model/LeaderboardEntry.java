package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "leaderboard")
@Getter
@Setter
public class LeaderboardEntry {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private int points;
    private int rank;
}