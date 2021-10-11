package com.tlcn.movieonline.dto;

import com.tlcn.movieonline.model.ReleaseYear;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieDetailRespone {
    private long id;
    private String description;
    private int duration;
    private ReleaseYear releaseYear;
    private String title;
    private String director;
    private String cast;
    private String genre;
    private String country;
}
