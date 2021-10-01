package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CastRepository extends JpaRepository<Cast, Long> {
    Cast getCastByName(String name);
}
