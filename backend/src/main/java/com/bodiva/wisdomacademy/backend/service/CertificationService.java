package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.model.Certification;
import com.bodiva.wisdomacademy.backend.repository.CertificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificationService {

    private final CertificationRepository certificationRepository;

    public CertificationService(CertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

    public List<Certification> getAllCertifications() {
        return certificationRepository.findAll();
    }

    public List<Certification> getCertificationsByUser(String userId) {
        return certificationRepository.findByUserId(userId);
    }

    public Optional<Certification> getById(Long id) {
        return certificationRepository.findById(id);
    }

    public Certification create(Certification cert) {
        return certificationRepository.save(cert);
    }

    public Certification update(Long id, Certification updated) {
        return certificationRepository.findById(id)
                .map(existing -> {
                    existing.setUserId(updated.getUserId());
                    existing.setCourseTitle(updated.getCourseTitle());
                    existing.setDescription(updated.getDescription());
                    existing.setImageUrl(updated.getImageUrl());
                    existing.setUserId(updated.getUserId());
                    return certificationRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Certification not found"));
    }

    public void delete(Long id) {
        certificationRepository.deleteById(id);
    }
}
