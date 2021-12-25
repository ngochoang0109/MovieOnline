package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "movievideo")
@Getter
@Setter
@NoArgsConstructor
public class MovieVideo {
    @EmbeddedId
    private MovieVideoKey id;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @MapsId("videoId")
    @JoinColumn(name = "video_id")
    private Video video;

    @Column(name = "current")
    private int current;

    public MovieVideo(Movie movie, Video video, int current) {
        this.id = new MovieVideoKey(movie.getId(), video.getId());
        this.movie = movie;
        this.video = video;
        this.current = current;
    }

}
