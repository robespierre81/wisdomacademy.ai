package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "userbadge")
@Getter
@Setter
public class UserBadge {
    @Id @GeneratedValue
    private Long id;
    private String username;

    @ManyToOne
    private Badge badge;

    private LocalDateTime awardedAt;
}