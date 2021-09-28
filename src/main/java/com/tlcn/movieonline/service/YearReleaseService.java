package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.ReleaseYear;

import java.util.List;

public interface YearReleaseService {
    ReleaseYear addYearRelease(ReleaseYear YearRelease);
    List<ReleaseYear> findAll();
    ReleaseYear getYearReleaseById(Long id);
    void deleteYearReleaseById(Long id);
}
