package com.tlcn.movieonline.controller.web;

import com.tlcn.movieonline.dto.MovieRespone;
import com.tlcn.movieonline.dto.RegisterRequest;
import com.tlcn.movieonline.model.Genre;
import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.model.User;
import com.tlcn.movieonline.service.GenreService;
import com.tlcn.movieonline.service.ImageService;
import com.tlcn.movieonline.service.MovieService;
import com.tlcn.movieonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){
        List<Movie> lstMovie=movieService.getAll();
        List<MovieRespone> newPosts= new ArrayList<>();
        for (Movie item: lstMovie) {
            MovieRespone movieRespone = new MovieRespone();
            movieRespone.setGenres(item.getGenres());
            movieRespone.setTitle(item.getTitle());
            movieRespone.setImg(item.getImages());
            newPosts.add(movieRespone);
        }
        List<Genre> lstGenre= genreService.findAll();
        model.addAttribute("lstGenre", lstGenre);
        model.addAttribute("newPosts", newPosts);
        return "/web/index";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        RegisterRequest registerRequest= new RegisterRequest();
        model.addAttribute("registerRequest",registerRequest);
        return "/register";
    }

    @RequestMapping(value = "/register", method=RequestMethod.POST)
    public String register(User user, @ModelAttribute RegisterRequest registerRequest, Model model) throws Exception {

        if(userService.emailExist(user.getEmail())){
            model.addAttribute("err", "Email already exists!");
            return "/web/register";
        }
        userService.registerAccount(registerRequest);
        return "/web/index";
    }

}
