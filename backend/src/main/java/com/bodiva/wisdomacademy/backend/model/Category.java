package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
public class Category {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String description;

    public Category() {
    }
}
