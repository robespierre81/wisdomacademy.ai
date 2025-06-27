package com.bodiva.wisdomacademy.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bodiva.wisdomacademy.backend.service", "com.bodemer.myarmo.database", "com.bodiva.wisdomacademy.backend.controller", "com.bodiva.wisdomacademy.config"})
public class Backend {
    public static void main(String[] args) {
        SpringApplication.run(Backend.class, args); 
    }
}