package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class MovieVideoKey implements Serializable {
    @Column(name = "movie-id")
    private Long movieId;

    @Column(name = "video-id")
    private Long videoId;

    public MovieVideoKey(Long movieId, Long videoId) {
        this.movieId = movieId;
        this.videoId = videoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieVideoKey that = (MovieVideoKey) o;

        if (movieId != that.movieId) return false;
        return videoId == that.videoId;
    }

    @Override
    public int hashCode() {
        int result = (int) (movieId ^ (movieId >>> 32));
        result = 31 * result + (int) (videoId ^ (videoId >>> 32));
        return result;
    }
}
