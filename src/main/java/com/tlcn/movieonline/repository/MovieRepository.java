package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie getMovieById(Long id);
}
