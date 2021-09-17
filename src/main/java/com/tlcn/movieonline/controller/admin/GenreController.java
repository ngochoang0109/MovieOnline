package com.tlcn.movieonline.controller.admin;

import com.tlcn.movieonline.model.Genre;
import com.tlcn.movieonline.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class GenreController {

    @Autowired
    GenreService genreService;

    @RequestMapping(value = "/genre-manager")
    public String listGenre(Model model){
        List<Genre> lstGenre= genreService.findAll();
        model.addAttribute("lstGenre", lstGenre);
                return "admin/genre-manager";
    }

}
