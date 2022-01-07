package com.tlcn.movieonline.service.impl;

import com.tlcn.movieonline.dto.MovieResponse;
import com.tlcn.movieonline.dto.MovieUserResponse;
import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.model.User;
import com.tlcn.movieonline.model.UserMovie;
import com.tlcn.movieonline.model.UserMovieKey;
import com.tlcn.movieonline.repository.UserMovieRepository;
import com.tlcn.movieonline.service.MovieService;
import com.tlcn.movieonline.service.UserMovieService;
import com.tlcn.movieonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserMovieServiceImpl implements UserMovieService {

    @Autowired
    private UserMovieRepository userMovieRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;


    @Override
    @Transactional
    @ExceptionHandler(Exception.class)
    public UserMovie add(User user, Movie movie) {
        UserMovie userMovie= new UserMovie(user, movie, 0, false);
        userMovieRepository.save(userMovie);
        return userMovie;
    }
    @Transactional
    @ExceptionHandler(Exception.class)
    @Override
    public UserMovie addToFavorite(User user, Movie movie) {
        UserMovie userMovie= new UserMovie(user, movie, 0, true);
        userMovieRepository.save(userMovie);
        return userMovie;
    }

    @Override
    public UserMovie getUserMovieByUserAndMovie(User user, Movie movie) {
        return userMovieRepository.getUserMovieByUserAndMovie(user, movie);
    }

    @Override
    public MovieResponse rating(long id, String email, float rate) {
        Movie movie= movieService.getMovieById(id);
        User user= userService.getUserByEmail(email);
        UserMovie objToSave= new UserMovie();
        try {
            UserMovie userMovie= this.getUserMovieByUserAndMovie(user, movie);
            userMovie.setRate(rate);
            if (rate>=4){
                userMovie.setFavorite(true);
            }else {
                if (userMovie.isFavorite()==false){
                    userMovie.setFavorite(false);
                }
            }
            objToSave=userMovie;
        }
        catch (RuntimeException runtimeException){
            UserMovie userMovie= new UserMovie();
            userMovie.setId(new UserMovieKey(user.getId(), movie.getId()));
            userMovie.setUser(user);
            userMovie.setMovie(movie);
            userMovie.setRate(rate);
            if (rate>=4){
                userMovie.setFavorite(true);
            }else {
                userMovie.setFavorite(false);
            }
            objToSave=userMovie;
        }
        finally {
            objToSave=userMovieRepository.save(objToSave);
        }

        MovieResponse movieResponse= new MovieResponse(objToSave.getRate());
        return movieResponse;
    }

    @Override
    public List<Float> calculatorRating(Movie movie) {
        // calculator rating
        List<Movie> movies=movieService.getMoviesByTitle(movie.getTitle());
        List<UserMovie> lstUserMovie=new LinkedList<>();
        for (Movie m:movies) {
            lstUserMovie.addAll(userMovieRepository.getUserMoviesByMovie(m));
        }
        for (UserMovie um:lstUserMovie) {
            if (um.getRate()==0){
                lstUserMovie.remove(um);
            }
        }
        if (lstUserMovie.size()==0){
            return new ArrayList<Float>(Arrays.asList((float)0,(float)0));
        }
        float totalRating=0;
        for (UserMovie item:lstUserMovie) {
            totalRating=totalRating+ item.getRate();
        }
        float rating=totalRating/lstUserMovie.size();
        BigDecimal bd = new BigDecimal(rating).setScale(2, RoundingMode.HALF_UP);

        List<Float> result= new ArrayList<>();
        result.add(bd.floatValue());
        result.add(rating);
        result.add((float)lstUserMovie.size());
        return result;
    }
}
