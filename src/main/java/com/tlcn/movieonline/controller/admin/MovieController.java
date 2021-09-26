package com.tlcn.movieonline.controller.admin;

import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/movies")
    public String getAllMovie(Model model){
        List<Movie> lstMovie= movieService.getAll();
        model.addAttribute("lstMovie", lstMovie);
        return "admin/movie-manager";
    }

    @GetMapping(value = "/movies/add")
    public String add(Model model){
        model.addAttribute("movie", new Movie());
        return "admin/movie-add";
    }

}
