package com.tlcn.movieonline.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieRequest {
    private String title;
    private String description;
    private int duration;
    private boolean status;
    private int number;
    private String cast;
    private String director;
    private MultipartFile image;
    private String videoTrailer;
    private MultipartFile videoMovie;
    private String country;
    private String genre;
    private Long releaseYear;
}
