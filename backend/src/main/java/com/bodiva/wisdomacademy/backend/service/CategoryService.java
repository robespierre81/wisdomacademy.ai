package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.model.Category;
import com.bodiva.wisdomacademy.backend.repository.CategoryRepository;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
