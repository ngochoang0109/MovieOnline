package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Genre;
import com.tlcn.movieonline.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();
    List<List<Movie>> findMoviesByGenreTenLimit();
    Movie addMove(Movie movie);
    Movie getMovieById(Long id);
}
