package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.ReleaseYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearReleaseRepository extends JpaRepository<ReleaseYear, Long > {
    ReleaseYear getYearReleaseById(Long id);
    void deleteById(Long id);
}
