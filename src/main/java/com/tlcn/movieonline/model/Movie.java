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
    private int number;

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

    @ManyToOne
    @JoinColumn(name = "relYearId")
    private ReleaseYear relYearId;

    @OneToMany(mappedBy = "movie")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "movie")
    private Set<UserMovie> userMovies;

    public Movie(){
        this.view=0;
    }
}
