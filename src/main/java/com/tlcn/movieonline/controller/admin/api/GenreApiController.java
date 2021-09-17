package com.tlcn.movieonline.controller.admin.api;

import com.tlcn.movieonline.model.Genre;
import com.tlcn.movieonline.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping(value = "/genre/edit")
    public @ResponseBody Genre editGenre(@RequestBody Genre g){
        Genre genre=genreService.addGenre(g);
        return genre;
    }

    @GetMapping(value = "/genre/edit")
    public @ResponseBody Genre editGenre(@RequestParam("id") Long id){
        Genre genre= genreService.getGenreById(id);
        return genre;
    }

    @DeleteMapping(value = "/genre/delete/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("id") Long id){
        genreService.deleteGenreById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
