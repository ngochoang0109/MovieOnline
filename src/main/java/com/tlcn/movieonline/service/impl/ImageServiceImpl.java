package com.tlcn.movieonline.service.impl;

import com.tlcn.movieonline.model.Image;
import com.tlcn.movieonline.repository.ImageRepository;
import com.tlcn.movieonline.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> add(List<Image> images) {

        return imageRepository.saveAll(images);
    }
}
