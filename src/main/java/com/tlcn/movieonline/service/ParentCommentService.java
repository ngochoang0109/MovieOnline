package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.ParentComment;

import java.util.List;

public interface ParentCommentService {
    ParentComment add(ParentComment parentComment);
    ParentComment getParentCommentById(Long id);
    List<ParentComment> getParentCommentByMovieId(Long id);
}
