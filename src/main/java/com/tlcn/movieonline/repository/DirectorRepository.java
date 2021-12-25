package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    Director[] getDirectorsByName(String name);
}
