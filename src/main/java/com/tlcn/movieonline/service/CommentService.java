package com.tlcn.movieonline.service;

import com.tlcn.movieonline.dto.CommentRequest;
import com.tlcn.movieonline.model.Comment;

import java.security.Principal;
import java.util.List;

public interface CommentService {
    Comment addComment(CommentRequest commentRequest, Principal principal);
    Comment getCommentByParentId(Long parentId);
}
