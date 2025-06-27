package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.model.UserLessonsView;
import com.bodiva.wisdomacademy.backend.repository.UserLessonsViewRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserLessonsViewService {
    private final UserLessonsViewRepository repository;

    public UserLessonsViewService(UserLessonsViewRepository repository) {
        this.repository = repository;
    }

    public List<UserLessonsView> getUserLessons(String walletAddress) {
        return repository.findByBookedBy(walletAddress);
    }
}
