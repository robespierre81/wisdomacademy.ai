package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.Badge;
import com.bodiva.wisdomacademy.backend.service.BadgeService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {

    private final BadgeService badgeService;

    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @GetMapping
    public List<Badge> getAll() {
        return badgeService.findAll();
    }

    @PostMapping
    public Badge create(@RequestBody Badge badge) {
        return badgeService.create(badge);
    }

    @PutMapping("/{id}")
    public Badge update(@PathVariable Long id, @RequestBody Badge badge) {
        return badgeService.update(id, badge);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        badgeService.delete(id);
    }
}
