package com.tlcn.movieonline.service.impl;

import com.tlcn.movieonline.model.Director;
import com.tlcn.movieonline.repository.DirectorRepository;
import com.tlcn.movieonline.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {
    @Autowired
    private DirectorRepository directorRepository;
    @Override
    public List<Director> getAllDirector() {
        return directorRepository.findAll();
    }

    @Override
    public Director add(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public Director getDirectorByName(String name) {
        return directorRepository.getDirectorByName(name);
    }
}
