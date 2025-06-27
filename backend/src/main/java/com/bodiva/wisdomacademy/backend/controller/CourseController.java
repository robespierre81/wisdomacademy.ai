package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.Course;
import com.bodiva.wisdomacademy.backend.repository.CourseRepository;
import com.bodiva.wisdomacademy.backend.service.CategoryService;
import java.util.UUID;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;
    private final CategoryService categoryService;

    public CourseController(CourseRepository courseRepository, CategoryService categoryService) {
        this.courseRepository = courseRepository;
        this.categoryService = categoryService;
    }

    // ✅ Add missing annotation
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/bycategory/{id}")
    public List<Course> getCoursesByCategory(@PathVariable Long id) {
        return courseRepository.findByCategoryId(id);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course savedCourse = courseRepository.save(course);
        return ResponseEntity.ok(savedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    existingCourse.setTitle(updatedCourse.getTitle());
                    existingCourse.setDescription(updatedCourse.getDescription());
                    existingCourse.setCategory(updatedCourse.getCategory());
                    // Add other fields as needed
                    Course saved = courseRepository.save(existingCourse);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        if (!courseRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        courseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my-courses")
    public ResponseEntity<List<Course>> getMyCourses(@RequestParam String userId) {
        List<Course> courses = courseRepository.findByUserId(userId);
        return courses.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(courses);
    }

    @PostMapping("/upload-image")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get("uploads/" + filename);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            String imageUrl = "/uploads/" + filename;

            Map<String, String> response = new HashMap<>();
            response.put("imageUrl", imageUrl);
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
