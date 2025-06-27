package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.MentorSession;
import com.bodiva.wisdomacademy.backend.repository.MentorSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/mentor-sessions")
public class MentorSessionController {

    @Autowired
    private MentorSessionRepository sessionRepository;

    @GetMapping
    public List<MentorSession> getAllSessions() {
        return sessionRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<MentorSession> getUserSessions(@PathVariable Long userId) {
        return sessionRepository.findByUserId(userId);
    }

    @PostMapping("/book")
    public MentorSession bookSession(@RequestBody MentorSession session) {
        session.setConfirmed(false);
        return sessionRepository.save(session);
    }

    @PostMapping
    public MentorSession createSession(@RequestBody MentorSession session) {
        return sessionRepository.save(session);
    }

    @PutMapping("/{id}/confirm")
    public MentorSession confirmSession(@PathVariable Long id) {
        MentorSession session = sessionRepository.findById(id).orElseThrow();
        session.setConfirmed(true);
        return sessionRepository.save(session);
    }
}