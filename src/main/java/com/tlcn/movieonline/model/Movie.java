package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "movie")
@Getter
@Setter
public class Movie{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    private int duration;
    private long view;
    private boolean status;
    private int number;
    private Date createDate;

    private int releaseYear;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "movies_images", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Collection<Image> images;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "movies_genres", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genres_id"))
    private Collection<Genre> genres;


    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "movies_casts", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "cast_id"))
    private Collection<Cast> casts;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "movies_countries", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Collection<Country> countries;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "movies_directors", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Collection<Director> directors;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Comment> comments;

    @OneToMany(mappedBy = "movie")
    private Collection<UserMovie> userMovies;

    @OneToMany(mappedBy = "movie")
    private Collection<MovieVideo> movieVideos;

    public Movie(){
        this.view=0;
        this.createDate=Calendar.getInstance().getTime();
    }

    public Movie(String title, String description, int duration, long view, boolean status, int number, int releaseYear, Collection<Image> images, Collection<Genre> genres, Collection<Cast> casts, Collection<Country> countries, Collection<Director> directors) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.view = view;
        this.status = status;
        this.number = number;
        this.releaseYear = releaseYear;
        this.images = images;
        this.genres = genres;
        this.casts = casts;
        this.countries = countries;
        this.directors = directors;
        this.createDate=Calendar.getInstance().getTime();
    }


}