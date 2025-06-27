package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.ContactMessage;
import com.bodiva.wisdomacademy.backend.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactMessageRepository contactRepo;

    @PostMapping
    public void sendMessage(@RequestBody ContactMessage message) {
        contactRepo.save(message);
        // Optional: send email notification to admin here
    }
}
