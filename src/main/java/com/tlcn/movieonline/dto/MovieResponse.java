package com.tlcn.movieonline.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieResponse {
    List<String> genres;
    String description;
    String title;
    long id;
    long view;
    String banner;
    float rate;

    public MovieResponse(List<String> genres, String description, String title, long id, long view, String banner) {
        this.genres = genres;
        this.description = description;
        this.title = title;
        this.id = id;
        this.view = view;
        this.banner = banner;
    }

    public MovieResponse(List<String> genres, String description, String title, long id, long view, String banner, float rate) {
        this.genres = genres;
        this.description = description;
        this.title = title;
        this.id = id;
        this.view = view;
        this.banner = banner;
        this.rate = rate;
    }

    public MovieResponse(float rate) {
        this.rate = rate;
    }
}
