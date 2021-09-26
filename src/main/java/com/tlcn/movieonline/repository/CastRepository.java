package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastRepository extends JpaRepository<Cast, Long> {

}
