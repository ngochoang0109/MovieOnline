package com.tlcn.movieonline.controller.web.api;

import com.tlcn.movieonline.dto.MovieUserRequest;
import com.tlcn.movieonline.dto.MovieUserResponse;
import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.model.User;
import com.tlcn.movieonline.model.UserMovie;
import com.tlcn.movieonline.service.MovieService;
import com.tlcn.movieonline.service.UserMovieService;
import com.tlcn.movieonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping(value = "/home/api")
public class ListFavoriteApiWebController {
    @Autowired
    private UserMovieService userMovieService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @PostMapping(value = "/add-favorite-list")
    public @ResponseBody MovieUserResponse addToFavoriteList(@RequestBody MovieUserRequest movieUserRequest, Principal principal){
        Movie movie= movieService.getMovieById(movieUserRequest.getId());
        User user= userService.getUserByEmail(principal.getName());
        UserMovie userMovie=userMovieService.add(user, movie);
        return new MovieUserResponse(movie.getTitle(),userMovie.getUser().getName());
    }
}
