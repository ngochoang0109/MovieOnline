package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Director;
import com.tlcn.movieonline.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService{
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
}
