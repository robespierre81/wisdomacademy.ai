package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.model.Comment;
import com.bodiva.wisdomacademy.backend.model.Discussion;
import java.util.List;

public interface DiscussionService {
    List<Discussion> findAll();
    Discussion create(Discussion d);

    public Comment addCommentToDiscussion(Long discussionId, Comment comment);

    public void voteDiscussion(Long discussionId, String type);

    public void voteComment(Long commentId, String type);
}