package com.tlcn.movieonline.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieWebController {

    @GetMapping("/movie")
    public String movieDetail(){
        return "web/movie-details";
    }
}
