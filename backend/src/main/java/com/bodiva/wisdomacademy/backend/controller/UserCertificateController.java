package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.Certification;
import com.bodiva.wisdomacademy.backend.repository.CertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usercertifications")
public class UserCertificateController {

    @Autowired
    private CertificationRepository certificationRepository;

    @GetMapping("/user/{walletAddress}")
    public List<Certification> getUserCertificates(@PathVariable String walletAddress) {
        return certificationRepository.findByUserId(walletAddress);
    }
}
