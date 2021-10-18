package com.tlcn.movieonline.controller.web;

import com.tlcn.movieonline.dto.MovieResponse;
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
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value ={"/","/home"} , method = RequestMethod.GET)
    public String index(Model model){
        List<List<Movie>> lstMovie=movieService.findMoviesByGenreTenLimit();

        List<Genre> lstGenre= genreService.findAll();
        model.addAttribute("theatersMovie",lstMovie.get(0));
        model.addAttribute("tvSeriesMovie", lstMovie.get(1));
        model.addAttribute("cartoonMovie", lstMovie.get(2));
        model.addAttribute("newPosts", lstMovie.get(3));
        model.addAttribute("lstGenre", lstGenre);
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
    public ModelAndView register(User user, @ModelAttribute RegisterRequest registerRequest, Model model) throws Exception {

        if(userService.emailExist(user.getEmail())){
            model.addAttribute("err", "Email already exists!");
            return new ModelAndView("redirect:" + "/register");
        }
        userService.registerAccount(registerRequest);
        return new ModelAndView("redirect:" + "/");
    }

}
