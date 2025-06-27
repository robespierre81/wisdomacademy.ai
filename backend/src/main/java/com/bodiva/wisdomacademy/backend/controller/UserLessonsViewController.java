package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.UserLessonsView;
import com.bodiva.wisdomacademy.backend.service.UserLessonsViewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userlessons")
public class UserLessonsViewController {
    private final UserLessonsViewService service;

    public UserLessonsViewController(UserLessonsViewService service) {
        this.service = service;
    }

    @GetMapping("/{walletAddress}")
    public List<UserLessonsView> getUserLessons(@PathVariable String walletAddress) {
        return service.getUserLessons(walletAddress);
    }
}
