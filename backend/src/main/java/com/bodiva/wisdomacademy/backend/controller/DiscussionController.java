package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.model.Comment;
import com.bodiva.wisdomacademy.backend.model.Discussion;
import com.bodiva.wisdomacademy.backend.service.DiscussionService;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/community/discussions")
public class DiscussionController {

    @Autowired
    private DiscussionService service;

    @GetMapping
    public List<Discussion> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Discussion post(@RequestBody Discussion discussion) {
        return service.create(discussion);
    }

    // ✅ Post a comment to a specific discussion
    @PostMapping("/{discussionId}/comments")
    public Comment addComment(@PathVariable Long discussionId, @RequestBody Comment comment) {
        return service.addCommentToDiscussion(discussionId, comment);
    }

    // ✅ Vote for a discussion (up/down)
    @PostMapping("/{discussionId}/vote")
    public void voteDiscussion(@PathVariable Long discussionId, @RequestBody Map<String, String> body) {
        String type = body.get("type"); // "up" or "down"
        service.voteDiscussion(discussionId, type);
    }
}
