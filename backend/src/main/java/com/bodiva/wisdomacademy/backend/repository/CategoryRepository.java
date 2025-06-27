package com.bodiva.wisdomacademy.backend.repository;

import com.bodiva.wisdomacademy.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
