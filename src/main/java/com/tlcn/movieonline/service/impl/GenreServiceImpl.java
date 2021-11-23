package com.tlcn.movieonline.service.impl;

import com.tlcn.movieonline.model.Genre;
import com.tlcn.movieonline.repository.GenreRepository;
import com.tlcn.movieonline.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Override
    @Transactional
    public Genre addGenre(Genre g) {
        return genreRepository.save(g);
    }

    @Override
    public List<Genre> findAll() {
        List<Genre> lstGenre=genreRepository.findAll();
        return lstGenre;
    }

    @Override
    public Genre getGenreById(Long id) {
        Genre genre= genreRepository.getGenreById(id);
        return genre;
    }

    @Override
    public void deleteGenreById(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public Genre getGenreByName(String name) {
        return genreRepository.getGenreByName(name);
    }

    @Override
    public Genre edit(Genre genre) {
        Genre g= genreRepository.getGenreById(genre.getId());
        g.setName(genre.getName());
        return genreRepository.save(g);
    }

    @Override
    public Genre[] getGenresByName(String genre) {
        return genreRepository.getGenresByName(genre);
    }
}
