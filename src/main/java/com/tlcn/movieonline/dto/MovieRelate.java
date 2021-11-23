package com.tlcn.movieonline.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieRelate {
    private String poster;

    public MovieRelate(String poster) {
        this.poster = poster;
    }
}
