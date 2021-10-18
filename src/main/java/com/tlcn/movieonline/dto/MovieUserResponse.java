package com.tlcn.movieonline.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieUserResponse {
    private String title;
    private String username;

    public MovieUserResponse(String title, String username) {
        this.title = title;
        this.username = username;
    }
}
