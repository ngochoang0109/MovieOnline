package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.YearRelease;

import java.util.List;

public interface YearReleaseService {
    YearRelease addYearRelease(YearRelease YearRelease);
    List<YearRelease> findAll();
    YearRelease getYearReleaseById(Long id);
    void deleteYearReleaseById(Long id);
}
