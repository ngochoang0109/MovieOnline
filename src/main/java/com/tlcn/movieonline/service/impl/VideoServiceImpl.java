package com.tlcn.movieonline.service.impl;

import com.tlcn.movieonline.model.Video;
import com.tlcn.movieonline.repository.VideoRepository;
import com.tlcn.movieonline.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;
    @Override
    public void addVideo(Video video) {
        videoRepository.save(video);
    }

    @Override
    public List<Video> getAll() {
        return videoRepository.findAll();
    }

    @Override
    public List<Video> addMultiVideo(List<Video> videos) {
        return videoRepository.saveAll(videos);
    }
}
