package com.tlcn.movieonline.controller.admin.api;

import com.tlcn.movieonline.model.Genre;
import com.tlcn.movieonline.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/api")
public class GenreApiController {

    @Autowired
    private GenreService genreService;

    @PostMapping(value = "/genre/add")
    public @ResponseBody Genre addGenre(@RequestBody Genre g) {
        Genre genre=genreService.addGenre(g);
        return genre;
    }
}
