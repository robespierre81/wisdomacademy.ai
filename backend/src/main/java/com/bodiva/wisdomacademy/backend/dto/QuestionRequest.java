package com.bodiva.wisdomacademy.backend.dto;

import java.util.List;
import lombok.Data;

@Data
public class QuestionRequest {
    private String text;
    private List<AnswerRequest> answers;
}