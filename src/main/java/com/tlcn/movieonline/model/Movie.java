package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movie")
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
    private String description;
    private int duration;
    private int view;
    private boolean status;

    @ManyToMany
    @JoinTable(
            name = "movieimage",
            joinColumns = @JoinColumn(name = "movieid"),
            inverseJoinColumns = @JoinColumn(name = "imageid")
    )
    private Set<Image> images;

    @ManyToMany
    @JoinTable(
            name = "movievideo",
            joinColumns = @JoinColumn(name = "movieid"),
            inverseJoinColumns = @JoinColumn(name = "videoid")
    )
    private Set<Video> videos;

    @ManyToMany
    @JoinTable(
            name = "moviegenre",
            joinColumns = @JoinColumn(name = "movieid"),
            inverseJoinColumns = @JoinColumn(name = "genreid")
    )
    private Set<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "moviecast",
            joinColumns = @JoinColumn(name = "movieid"),
            inverseJoinColumns = @JoinColumn(name = "castid")
    )
    private Set<Cast> casts;

    @ManyToMany
    @JoinTable(
            name = "moviecoutry",
            joinColumns = @JoinColumn(name = "movieid"),
            inverseJoinColumns = @JoinColumn(name = "coutryid")
    )
    private Set<Country> countries;

    @ManyToMany
    @JoinTable(
            name = "moviedirector",
            joinColumns = @JoinColumn(name = "movieid"),
            inverseJoinColumns = @JoinColumn(name = "directorid")
    )
    private Set<Director> directors;

    @ManyToMany
    @JoinTable(
            name = "moviekeyword",
            joinColumns = @JoinColumn(name = "movieid"),
            inverseJoinColumns = @JoinColumn(name = "keywordid")
    )
    private Set<KeyWord> keyWords;

    @ManyToMany
    @JoinTable(
            name = "movierelease",
            joinColumns = @JoinColumn(name = "movieid"),
            inverseJoinColumns = @JoinColumn(name = "releaseid")
    )
    private Set<YearRelease> yearReleases;

    @OneToMany(mappedBy = "movie")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "movie")
    private Set<UserMovie> userMovies;

    public Movie(String title, String description, int duration, int view, boolean status) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.view = view;
        this.status = status;
    }

    public Movie(){
        this.view=0;
    }
}
