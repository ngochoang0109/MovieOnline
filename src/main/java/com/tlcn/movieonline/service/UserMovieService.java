package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.model.User;
import com.tlcn.movieonline.model.UserMovie;

public interface UserMovieService {
    UserMovie add(User user, Movie movie);
    UserMovie getUserMovieByUserAndMovie(User user, Movie movie);
}
