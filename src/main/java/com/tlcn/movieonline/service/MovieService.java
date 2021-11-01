package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Movie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {
    Page<Movie> findAll(int currentPage);
    List<List<Movie>> findMoviesByGenreTenLimit();
    Movie addMove(Movie movie);
    Movie getMovieById(Long id);
    List<Movie> getMyList(String email);
    List<Movie> getMovieByGenre(String genre);
}
