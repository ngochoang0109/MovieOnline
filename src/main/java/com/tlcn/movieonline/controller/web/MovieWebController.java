package com.tlcn.movieonline.controller.web;

import com.tlcn.movieonline.dto.CommentResponse;
import com.tlcn.movieonline.dto.MovieDetailResponse;
import com.tlcn.movieonline.model.*;
import com.tlcn.movieonline.service.CommentService;
import com.tlcn.movieonline.service.MovieService;
import com.tlcn.movieonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;


@Controller
public class MovieWebController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;


    @GetMapping("/home/movie")
    public String movieDetail(@RequestParam("id") Long id, Model model, Principal principal){
        User user= userService.getUserByEmail(principal.getName());
        System.out.println(user.getName());

        Movie movie=movieService.getMovieById(id) ;
        MovieDetailResponse movieDetail= new MovieDetailResponse();
        movieDetail.setTitle(movie.getTitle());
        movieDetail.setDescription(movie.getDescription());
        movieDetail.setDuration(movie.getDuration());
        movieDetail.setReleaseYear(movie.getRelYearId());

        StringJoiner joinerDirector= new StringJoiner(", ");
        for (Director d:movie.getDirectors()) {
            joinerDirector.add(d.getName());
        }
        movieDetail.setDirector(joinerDirector.toString());

        StringJoiner joinerCast= new StringJoiner(", ");
        for (Cast c:movie.getCasts()) {
            joinerCast.add(c.getName());
        }
        movieDetail.setCast(joinerCast.toString());

        StringJoiner joinerGenre= new StringJoiner(", ");
        for (Genre g:movie.getGenres()) {
            joinerGenre.add(g.getName());
        }
        movieDetail.setGenre(joinerGenre.toString());

        StringJoiner joinerCountry= new StringJoiner(", ");
        for (Country c:movie.getCountries()) {
            joinerCountry.add(c.getName());
        }
        movieDetail.setCountry(joinerCountry.toString());

        Set<Comment> lstComment=movie.getComments();
        List<CommentResponse> lstCommentResponse= new LinkedList<>();
        for (Comment item: lstComment) {
            CommentResponse commentResponse= new CommentResponse();
            commentResponse.setContent(item.getContent());
            commentResponse.setCreateDate(item.getCreateDate());
            commentResponse.setUsername("ngoc hoang");
            lstCommentResponse.add(commentResponse);
        }

        movieDetail.setLstComment(lstCommentResponse);
        model.addAttribute("movie", movieDetail);

        return "web/movie-details";
    }

    @GetMapping("/home/movie/watch")
    public String movieWatch(){
        return "web/movie-watch";
    }

}
