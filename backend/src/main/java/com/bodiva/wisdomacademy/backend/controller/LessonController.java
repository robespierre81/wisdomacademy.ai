package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.*;
import com.bodiva.wisdomacademy.backend.repository.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonRepository lessonRepository;
    private final LessonContentRepository lessonContentRepository;

    public LessonController(LessonRepository lessonRepository, LessonContentRepository lessonContentRepository) {
        this.lessonRepository = lessonRepository;
        this.lessonContentRepository = lessonContentRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadLessonFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String type,
            @RequestParam("title") String title,
            @RequestParam("courseId") Long courseId
    ) {
        try {
            // 1. Save Lesson metadata
            Lesson lesson = new Lesson();
            lesson.setTitle(title);
            lesson.setType(type);
            lesson.setCourseId(courseId);
            lesson.setDuration(0); // or calculate if needed
            lesson.setStatus("draft"); // or "active"
            lesson.setCreatedAt(LocalDateTime.now());
            lesson = lessonRepository.save(lesson);

            // 2. Save the file as bytea in lesson_contents
            LessonContent content = new LessonContent();
            content.setLesson(lesson);
            content.setType(type);
            content.setContent(file.getBytes());
            lessonContentRepository.save(content);

            // 3. Return response
            Map<String, Object> response = new HashMap<>();
            response.put("lessonId", lesson.getId());
            response.put("status", "success");
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
