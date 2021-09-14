package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Genre;
import com.tlcn.movieonline.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

    @Autowired
    GenreRepository genreRepository;

    @Override
    public void addGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public List<Genre> findAll() {
        List<Genre> lstGenre=genreRepository.findAll();
        return lstGenre;
    }
}
