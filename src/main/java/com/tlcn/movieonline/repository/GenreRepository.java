package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long > {
    Genre getGenreById(Long id);
    void deleteById(Long id);
    Genre getGenreByName(String name);
    Genre[] getGenresByName(String name);
}
