package com.tlcn.movieonline.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieUserRequest {
    private long id;

    public MovieUserRequest(long id) {
        this.id = id;
    }
}
