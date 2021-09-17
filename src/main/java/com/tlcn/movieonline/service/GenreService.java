package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Genre;

import java.util.List;

public interface GenreService {
    Genre addGenre(Genre genre);
    List<Genre> findAll();
}
