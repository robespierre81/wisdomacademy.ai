package com.bodiva.wisdomacademy.backend.controller;

import com.bodiva.wisdomacademy.backend.service.DiscussionService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/community/comments")
public class CommentController {

    @Autowired
    private DiscussionService discussionService;

    @PostMapping("/{commentId}/vote")
    public void voteComment(@PathVariable Long commentId, @RequestBody Map<String, String> body) {
        String type = body.get("type");
        discussionService.voteComment(commentId, type);
    }
}
