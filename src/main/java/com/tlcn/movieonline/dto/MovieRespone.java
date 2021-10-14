package com.tlcn.movieonline.dto;

import com.tlcn.movieonline.model.Genre;
import com.tlcn.movieonline.model.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MovieRespone {
    private Long id;
    private String title;
    private Set<Genre> genres;
    private Set<Image> img;
    private List<CommentResponse> commentRespones;
}
