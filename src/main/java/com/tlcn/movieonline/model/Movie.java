package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    private int duration;
    private int view;
    private boolean status;
    private int number;


    public Set<MovieImage> getImages() {
        return images;
    }

    public void setImages(Set<MovieImage> images) {
        this.images = images;
    }

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<MovieImage> images = new HashSet<>();

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    private String mainImage;

    public Set<MovieVideo> getVideos() {
        return videos;
    }

    public void setVideos(Set<MovieVideo> videos) {
        this.videos = videos;
    }

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MovieVideo> videos = new HashSet<>();

    private String trailerVideo;



    @ManyToMany()
    @JoinTable(name = "movies_genres", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genres_id"))
    private Set<Genre> genres = new HashSet<>();


    @ManyToMany()
    @JoinTable(name = "movies_casts", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "cast_id"))
    private Set<Cast> casts = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "movies_countries", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> countries = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "movies_directors", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Director> directors = new HashSet<>();


    private Integer yearRelease;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;

    @OneToMany(mappedBy = "movie")
    private Set<UserMovie> userMovies;

    public Movie(){
        this.view=0;
    }
}
