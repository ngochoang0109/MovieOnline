package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.MovieVideo;
import com.tlcn.movieonline.model.MovieVideoKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieVideoRepository extends JpaRepository<MovieVideo, MovieVideoKey> {

    List<MovieVideo> getMovieVideosByMovieId(long movieId);
}
