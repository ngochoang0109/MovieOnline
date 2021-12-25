package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Director;

import java.util.List;

public interface DirectorService {
    List<Director> getAllDirector();
    Director add(Director director);
    Director[] getDirectorsByName(String name);
}
