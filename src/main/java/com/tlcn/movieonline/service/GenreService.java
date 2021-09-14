package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Genre;

import java.util.List;

public interface GenreService {
    void addGenre(Genre genre);
    List<Genre> findAll();
}
