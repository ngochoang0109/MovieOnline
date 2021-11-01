package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Video;

import java.util.List;

public interface VideoService {
    void addVideo(Video video);
    List<Video> addMultiVideo(List<Video> videos);
    List<Video> getAll();
}
