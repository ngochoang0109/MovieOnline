package com.tlcn.movieonline.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieDetailResponse {
    private long id;
    private String description;
    private int duration;
    private int releaseYear;
    private String title;
    private String director;
    private String cast;
    private String genre;
    private String country;
    private String img;
    private String trailer;
    private long view;
    private float rating;
    private int totalRating;
    private int number;
    private int currentEpisode;
}
