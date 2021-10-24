package com.tlcn.movieonline.model;

import javax.persistence.*;

@Entity
public class MovieVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public MovieVideo() {

    }

    public MovieVideo(Integer id, String name, Movie movie) {
        this.id = id;
        this.name = name;
        this.movie = movie;
    }

    public MovieVideo(String name, Movie movie) {
        this.name = name;
        this.movie = movie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Transient
    public String getImagePath() {
        return "/movie-images/" + movie.getId() + "/extras/" + this.getName();
    }


}
