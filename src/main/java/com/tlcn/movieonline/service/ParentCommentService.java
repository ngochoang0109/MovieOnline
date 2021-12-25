package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Page;
import com.tlcn.movieonline.model.ParentComment;

import java.util.List;

public interface ParentCommentService {
    ParentComment add(ParentComment parentComment);
    ParentComment getParentCommentById(Long id);
    Page<ParentComment> getParentCommentByMovieId(Long id, int currentPage);
    List<ParentComment> paginationAndSortingTime(List<ParentComment> parentComments, int currentPage);
    int totalPage(List<ParentComment> parentComments);
}
