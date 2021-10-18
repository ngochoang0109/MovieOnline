package com.tlcn.movieonline.dto;

import com.tlcn.movieonline.model.Image;
import com.tlcn.movieonline.model.ReleaseYear;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MovieDetailResponse {
    private long id;
    private String description;
    private int duration;
    private ReleaseYear releaseYear;
    private String title;
    private String director;
    private String cast;
    private String genre;
    private String country;
    private String img;
    private String trailer;
    private int view;
}
