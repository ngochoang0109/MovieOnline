package com.tlcn.movieonline.controller.web;

import com.tlcn.movieonline.dto.MovieDetailRespone;
import com.tlcn.movieonline.model.*;
import com.tlcn.movieonline.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.StringJoiner;
import java.util.stream.Collectors;

@Controller
public class MovieWebController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie")
    public String movieDetail(@RequestParam("id") Long id, Model model){
        Movie movie=movieService.getMovieById(id) ;
        MovieDetailRespone movieDetail= new MovieDetailRespone();
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




        model.addAttribute("movie", movieDetail);
        return "web/movie-details";
    }

}
