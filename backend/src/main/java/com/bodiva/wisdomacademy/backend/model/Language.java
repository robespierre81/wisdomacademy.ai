package com.bodiva.wisdomacademy.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Language {
    ENGLISH("english"),
    GERMAN("german"),
    FRENCH("french"),
    SPANISH("spanish"),
    CHINESE("chinese"),
    RUSSIAN("russian")
    ;

    private final String value;

    Language(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static Language fromValue(String value) {
        for (Language status : Language.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}