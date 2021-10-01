package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Cast;
import com.tlcn.movieonline.repository.CastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CastServiceImpl implements CastService{

    @Autowired
    private CastRepository castRepository;
    @Override
    public List<Cast> getAllCast() {
        List<Cast> lstCast= castRepository.findAll();
        return lstCast;
    }

    @Override
    public Cast add(Cast cast) {
        return castRepository.save(cast);
    }

    @Override
    public Cast getCastByName(String name) {
        return castRepository.getCastByName(name);
    }
}
