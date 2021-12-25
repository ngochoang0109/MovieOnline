package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.model.User;
import com.tlcn.movieonline.model.UserMovie;
import com.tlcn.movieonline.model.UserMovieKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMovieRepository extends JpaRepository<UserMovie, UserMovieKey> {
    UserMovie getUserMovieByUserAndMovie(User user, Movie movie);
    List<UserMovie> getUserMoviesByMovie(Movie movie);
}
