package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Genre;
import com.tlcn.movieonline.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

    @Autowired
    GenreRepository genreRepository;

    @Override
    @Transactional
    public Genre addGenre(Genre g) {
        Genre genre= genreRepository.save(g);
        return genre;
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
}
