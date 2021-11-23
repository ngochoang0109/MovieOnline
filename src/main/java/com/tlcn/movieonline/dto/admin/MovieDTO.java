package com.tlcn.movieonline.dto.admin;

import com.tlcn.movieonline.constant.MovieConstant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class MovieDTO {
    private String title;
    private String description;
    private int duration;
    private boolean status;
    private int number;
    private int current;
    private String cast;
    private String director;
    private MultipartFile imagePoster;
    private MultipartFile imageWatch;
    private String videoTrailer;
    private MultipartFile videoMovie;
    private String country;
    private String genre;
    private int releaseYear;
}
