package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.ParentComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentCommentRepository extends JpaRepository<ParentComment, Long> {
    ParentComment getParentCommentById(Long id);
}
