package com.bodiva.wisdomacademy.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "assessment_details_view") // Adjust if your actual DB view/table name is different
@Getter
@Setter
public class AssessmentDetailsView {

    @Id
    private Long id;

    private String title;
    private String description;
    private int totalQuestions;
    private BigDecimal price;

    public AssessmentDetailsView() {}

    public AssessmentDetailsView(Long id, String title, String description, int totalQuestions, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.totalQuestions = totalQuestions;
        this.price = price;
    }
}
