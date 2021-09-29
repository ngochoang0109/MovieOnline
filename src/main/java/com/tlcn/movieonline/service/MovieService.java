package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();
    Movie addMove(Movie movie);
}
