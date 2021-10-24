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
        movieDetail.setTitle(movie.getTitle());
        movieDetail.setDescription(movie.getDescription());
        movieDetail.setDuration(movie.getDuration());
//        movieDetail.setReleaseYear(movie.getRelYearId());
        movieDetail.setView(movie.getView());

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

        // Dung tam {*
        String img="";
        for (MovieImage item: movie.getImages()) {
            img=item.getName();
            break;
        }
        movieDetail.setImg(img);

        String video="";
//        for (Video item: movie.getVideos()) {
//            video=item.getSource();
//            break;
//        }
        movieDetail.setTrailer(video);
        //*}

        List<ParentComment> lstParentComment= parentCommentService.getParentCommentByMovieId(id);
        CommentRequest commentRequest= new CommentRequest();
        model.addAttribute("lstParentComment", lstParentComment);
        model.addAttribute("commentRequest", commentRequest);
        model.addAttribute("movie", movieDetail);

        return "web/movie-details";
    }

    @GetMapping("/home/movie/watch")
    public String movieWatch(){
        return "web/movie-watch";
    }

}
