package com.tlcn.movieonline.controller.web;


import com.tlcn.movieonline.dto.MovieDetailResponse;
import com.tlcn.movieonline.dto.MovieResponse;
import com.tlcn.movieonline.model.*;
import com.tlcn.movieonline.service.MovieService;
import com.tlcn.movieonline.service.ParentCommentService;
import com.tlcn.movieonline.service.UserMovieService;
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
    private ParentCommentService parentCommentService;

    @Autowired
    private UserMovieService userMovieService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/home/movie")
    public String movieDetailDefault(@RequestParam(value = "id") Long id, Model model, Principal principal){
        return movieDetail(1,id, model, principal);
    }


    @GetMapping("/home/movie/{page}")
    public String movieDetail(@PathVariable("page") int page, @RequestParam(value = "id") Long id, Model model, Principal principal){

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

        Page<ParentComment> parentCommentPage= parentCommentService.getParentCommentByMovieId(id, page);
        List<ParentComment> lstParentComment= parentCommentPage.getLstData();

        List<Float> rating= userMovieService.calculatorRating(movie);
        movieDetail.setRating(rating.get(0));
        movieDetail.setTotalRating(Math.round(rating.get(1)));

        model.addAttribute("lstParentComment", lstParentComment);
        model.addAttribute("movie", movieDetail);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", parentCommentPage.getTotalPage());
        return "web/movie/movie-details";
    }

    @GetMapping("/home/movie/watch")
    public String getMovieWatch(@RequestParam("id") long id, Model model, Principal principal){
        return movieWatch(1,id, model, principal);
    }

    @GetMapping("/home/movie/watch/{page}")
    public String movieWatch(@PathVariable("page") int page,@RequestParam("id") long id, Model model, Principal principal){
        Movie movie= movieService.countView(id);
        User user= userService.getUserByEmail(principal.getName());
        UserMovie userMovie= userMovieService.getUserMovieByUserAndMovie(user,movie);
        List<String> genre= new LinkedList<>();
        String banner="";

        movie.getGenres().stream().forEach(item->genre.add(item.getName()));
        for (Image item: movie.getImages()) {
            if (item.getType().equals("watch")){
                banner=item.getSource();
            }
        }

        MovieResponse movieResponse=
                new MovieResponse(genre, movie.getDescription(),
                        movie.getTitle(), movie.getId(), movie.getView(),banner, userMovie.getRate());

        Page<ParentComment> parentCommentPage= parentCommentService.getParentCommentByMovieId(id, page);
        List<ParentComment> lstParentComment= parentCommentPage.getLstData();

        model.addAttribute("movie", movieResponse);
        model.addAttribute("lstParentComment", lstParentComment);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", parentCommentPage.getTotalPage());
        return "web/movie/movie-watch";
    }

    @GetMapping("/movies/{genre}")
    public String searchByGenre(@PathVariable("genre") String genre, Model model){
        List<Movie> movies= movieService.getMovieByGenre(genre);
        model.addAttribute("movies", movies);
        return "/web/movie/search-movie";
    }



}
