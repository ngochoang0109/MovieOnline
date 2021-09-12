package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usermovie")
@Getter
@Setter
@NoArgsConstructor
public class UserMovie {
    @EmbeddedId
    private UserMovieKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movieid")
    private Movie movie;

    @Column(name = "rate")
    private int rate;
    @Column(name = "favorite")
    private boolean favorite;

    public UserMovie(User user, Movie movie, int rate, boolean favorite) {
        this.user = user;
        this.movie = movie;
        this.rate = rate;
        this.favorite = favorite;
    }
}
