package com.tlcn.movieonline.controller.web;


import com.tlcn.movieonline.dto.CommentRequest;
import com.tlcn.movieonline.dto.MovieDetailResponse;
import com.tlcn.movieonline.model.*;
import com.tlcn.movieonline.service.CommentService;
import com.tlcn.movieonline.service.MovieService;
import com.tlcn.movieonline.service.ParentCommentService;
import com.tlcn.movieonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private ParentCommentService parentCommentService;


    @GetMapping("/home/movie")
    public String movieDetail(@RequestParam("id") Long id, Model model, Principal principal){

        Movie movie=movieService.getMovieById(id);
        MovieDetailResponse movieDetail= new MovieDetailResponse();
        movieDetail.setId(movie.getId());
        movieDetail.setDescription(movie.getDescription());
        movieDetail.setDuration(movie.getDuration());
        movieDetail.setReleaseYear(movie.getReleaseYear());
        movieDetail.setTitle(movie.getTitle());

        movieDetail.setView(movie.getView());

        for (Image item: movie.getImages()) {
            if (item.getType().equals("poster")){
                movieDetail.setImg(item.getSource());
                break;
            }
        }

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


        String video="";
        for (MovieVideo movieVideo: movie.getMovieVideos()) {
            if (movieVideo.getVideo().getType().equals("trailer")){
                video=movieVideo.getVideo().getSource();
                break;
            }
        }
        movieDetail.setTrailer(video);


        List<ParentComment> lstParentComment= parentCommentService.getParentCommentByMovieId(id);
        CommentRequest commentRequest= new CommentRequest();
        model.addAttribute("lstParentComment", lstParentComment);
        model.addAttribute("commentRequest", commentRequest);
        model.addAttribute("movie", movieDetail);

        return "web/movie/movie-details";
    }

    @GetMapping("/home/movie/watch")
    public String movieWatch(@RequestParam("id") long id, Model model){
        Movie movie= movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "web/movie/movie-watch";
    }

    @GetMapping("/movies/{genre}")
    public String searchByGenre(@PathVariable("genre") String genre, Model model){
        List<Movie> movies= movieService.getMovieByGenre(genre);
        model.addAttribute("movies", movies);
        return "/web/movie/search-movie";
    }



}
