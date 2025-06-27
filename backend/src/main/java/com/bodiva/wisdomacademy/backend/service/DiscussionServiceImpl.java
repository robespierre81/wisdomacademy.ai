package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.model.Comment;
import com.bodiva.wisdomacademy.backend.model.Discussion;
import com.bodiva.wisdomacademy.backend.repository.CommentRepository;
import com.bodiva.wisdomacademy.backend.repository.DiscussionRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscussionServiceImpl implements DiscussionService {
    @Autowired private DiscussionRepository discussionRepo;
    @Autowired private CommentRepository commentRepo;

    @Override
    public List<Discussion> findAll() {
        return discussionRepo.findAll();
    }

    @Override
    public Discussion create(Discussion d) {
        d.setCreatedAt(LocalDateTime.now());
        d.setUpvotes(0);
        d.setDownvotes(0);
        return discussionRepo.save(d);
    }

    @Override
    @Transactional
    public Comment addCommentToDiscussion(Long discussionId, Comment comment) {
        Discussion discussion = discussionRepo.findById(discussionId)
            .orElseThrow(() -> new IllegalArgumentException("Discussion not found"));
        comment.setCreatedAt(LocalDateTime.now());
        comment.setDiscussion(discussion);
        comment.setUpvotes(0);
        comment.setDownvotes(0);
        return commentRepo.save(comment);
    }

    @Override
    @Transactional
    public void voteDiscussion(Long discussionId, String type) {
        Optional<Discussion> opt = discussionRepo.findById(discussionId);
        if (opt.isPresent()) {
            Discussion d = opt.get();
            if ("up".equalsIgnoreCase(type)) {
                d.setUpvotes(d.getUpvotes() + 1);
            } else if ("down".equalsIgnoreCase(type)) {
                d.setDownvotes(d.getDownvotes() + 1);
            }
            discussionRepo.save(d);
        }
    }

    @Transactional
    public void voteComment(Long commentId, String type) {
        Optional<Comment> opt = commentRepo.findById(commentId);
        if (opt.isPresent()) {
            Comment c = opt.get();
            if ("up".equalsIgnoreCase(type)) {
                c.setUpvotes(c.getUpvotes() + 1);
            } else if ("down".equalsIgnoreCase(type)) {
                c.setDownvotes(c.getDownvotes() + 1);
            }
            commentRepo.save(c);
        }
    }
}
