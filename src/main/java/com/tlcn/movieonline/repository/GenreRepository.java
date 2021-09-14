package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long > {
}
