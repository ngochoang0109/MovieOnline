package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Cast;

import java.util.List;

public interface CastService {
    List<Cast> getAllCast();
    Cast add(Cast cast);
    Cast getCastByName(String name);
}
