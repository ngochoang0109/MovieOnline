package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.model.User;
import com.tlcn.movieonline.model.UserMovie;
import com.tlcn.movieonline.repository.UserMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMovieServiceImpl implements UserMovieService{

    @Autowired
    private UserMovieRepository userMovieRepository;

    @Override
    public UserMovie add(User user, Movie movie) {
        UserMovie userMovie= new UserMovie(user, movie, 0, false);
        userMovieRepository.save(userMovie);
        return userMovie;
    }

    @Override
    public UserMovie getUserMovieByUserAndMovie(User user, Movie movie) {
        return userMovieRepository.getUserMovieByUserAndMovie(user, movie);
    }
}
