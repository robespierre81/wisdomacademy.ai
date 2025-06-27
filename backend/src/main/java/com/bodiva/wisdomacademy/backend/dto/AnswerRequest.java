package com.bodiva.wisdomacademy.backend.dto;

import lombok.Data;

@Data
public class AnswerRequest {
    private String text;
    private boolean correct = false;
}