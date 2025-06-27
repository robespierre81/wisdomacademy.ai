package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.model.Badge;
import com.bodiva.wisdomacademy.backend.repository.BadgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BadgeService {

    private final BadgeRepository badgeRepository;

    public BadgeService(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    public List<Badge> findAll() {
        return badgeRepository.findAll();
    }

    public Optional<Badge> findById(Long id) {
        return badgeRepository.findById(id);
    }

    public Badge create(Badge badge) {
        return badgeRepository.save(badge);
    }

    public Badge update(Long id, Badge badgeData) {
        return badgeRepository.findById(id).map(b -> {
            b.setTitle(badgeData.getTitle());
            b.setDescription(badgeData.getDescription());
            b.setIconurl(badgeData.getIconurl());
            b.setPoints(badgeData.getPoints());
            b.setRank(badgeData.getRank());
            return badgeRepository.save(b);
        }).orElseThrow(() -> new RuntimeException("Badge not found"));
    }

    public void delete(Long id) {
        badgeRepository.deleteById(id);
    }
}
