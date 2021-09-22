package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Image;
import com.tlcn.movieonline.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getAll() {
        List<Image> lstImage=imageRepository.findAll();
        return lstImage;
    }

    @Override
    public void addImage(Image image) {
        Image i=imageRepository.save(image);
    }
}
