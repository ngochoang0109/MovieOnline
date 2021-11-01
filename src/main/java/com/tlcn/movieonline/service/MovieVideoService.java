package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.model.MovieVideo;
import com.tlcn.movieonline.model.Video;

import java.util.List;

public interface MovieVideoService {
    MovieVideo addMovieVideo(Movie movie, Video video, int current);
    List<MovieVideo> addOneMovieMultiVideo(Movie movie, List<Video> video, int current);
}
