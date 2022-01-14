package com.tlcn.movieonline.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.exception.DataException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter

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
    private Date createDate;
    public MovieDTO() {
        this.createDate=Calendar.getInstance().getTime();
    }

    public MovieDTO(String title) {
        this.title = title;
    }
}
