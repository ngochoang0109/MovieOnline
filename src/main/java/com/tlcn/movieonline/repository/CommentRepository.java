package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment,Long> {

    @Query(
            "select comment from Comment comment where comment.movie.id=:id order by comment.createDate desc"
    )
    List<Comment> getCommentsByMovieId(@Param("id") Long id, Pageable pageable);
}
