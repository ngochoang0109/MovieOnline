package com.tlcn.movieonline.service;

import com.tlcn.movieonline.dto.CommentRequest;
import com.tlcn.movieonline.model.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(CommentRequest commentRequest);
    List<Comment> getAllCommentByProductId(Long id);
}
