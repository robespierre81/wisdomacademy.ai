package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.Certification;
import com.bodiva.wisdomacademy.backend.repository.CertificationRepository;
import com.bodiva.wisdomacademy.backend.service.CertificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    private final CertificationService certificationService;
    @Autowired
    private CertificationRepository certificationRepository;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @GetMapping
    public List<Certification> getAll() {
        return certificationService.getAllCertifications();
    }

    @GetMapping("/user/{userId}")
    public List<Certification> getByUser(@PathVariable String userId) {
        return certificationService.getCertificationsByUser(userId);
    }

    @PostMapping
    public Certification create(@RequestBody Certification cert) {
        return certificationService.create(cert);
    }

    @PutMapping("/{id}")
    public Certification updateCertification(@PathVariable Long id, @RequestBody Certification updated) {
        return certificationRepository.findById(id).map(cert -> {
            cert.setCourseTitle(updated.getCourseTitle());
            cert.setDescription(updated.getDescription());
            cert.setImageUrl(updated.getImageUrl());
            return certificationRepository.save(cert);
        }).orElseThrow(() -> new RuntimeException("Certification not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        certificationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
