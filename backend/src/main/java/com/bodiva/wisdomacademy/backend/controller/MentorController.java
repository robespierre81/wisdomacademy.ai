package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.Mentor;
import com.bodiva.wisdomacademy.backend.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentors")
public class MentorController {

    @Autowired
    private MentorRepository mentorRepository;

    @GetMapping
    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    @GetMapping("/available")
    public List<Mentor> getAvailableMentors() {
        return mentorRepository.findByIsAvailable(true);
    }

    @PostMapping("/register")
    public Mentor becomeMentor(@RequestBody Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    @PostMapping
    public Mentor createMentor(@RequestBody Mentor mentor) {
        return mentorRepository.save(mentor);
    }
}