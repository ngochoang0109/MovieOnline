package com.tlcn.movieonline.service.impl;

import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.model.MovieVideo;
import com.tlcn.movieonline.model.Video;
import com.tlcn.movieonline.repository.MovieVideoRepository;
import com.tlcn.movieonline.service.MovieService;
import com.tlcn.movieonline.service.MovieVideoService;
import com.tlcn.movieonline.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieVideoServiceImpl implements MovieVideoService {

    @Autowired
    private MovieVideoRepository movieVideoRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private VideoService videoService;

    @Override
    public MovieVideo addMovieVideo(Movie movie, Video video, int current) {
        return movieVideoRepository.save(new MovieVideo(movie, video, current));
    }

    @Override
    public List<MovieVideo> addOneMovieMultiVideo(Movie movie, List<Video> videos, int current) {
        Movie m=movieService.addMove(movie);
        List<Video> lstVideo= videoService.addMultiVideo(videos);
        List<MovieVideo> lstMovievideo= new ArrayList<>();
        for (Video video: lstVideo){
            MovieVideo movieVideo= addMovieVideo(m, video, current);
            lstMovievideo.add(movieVideo);
        }
        return lstMovievideo;
    }
}
