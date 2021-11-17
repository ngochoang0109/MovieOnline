package com.tlcn.movieonline.service;

import com.tlcn.movieonline.dto.MovieResponse;
import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.model.User;
import com.tlcn.movieonline.model.UserMovie;

import java.util.List;

public interface UserMovieService {
    UserMovie add(User user, Movie movie);
    UserMovie getUserMovieByUserAndMovie(User user, Movie movie);
    MovieResponse rating(long id, String email, float rate);
    List<Float> calculatorRating(Movie movie);
}
