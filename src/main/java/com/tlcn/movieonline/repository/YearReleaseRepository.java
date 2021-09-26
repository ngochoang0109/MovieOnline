package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.YearRelease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearReleaseRepository extends JpaRepository<YearRelease, Long > {
    YearRelease getYearReleaseById(Long id);
    void deleteById(Long id);
}
