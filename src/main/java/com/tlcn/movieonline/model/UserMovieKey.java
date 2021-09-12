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
public class UserMovieKey implements Serializable {

    @Column(name = "userid")
    private Long userId;

    @Column(name = "movieid")
    private Long movieId;

    public UserMovieKey(long userId, long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMovieKey that = (UserMovieKey) o;

        if (userId != that.userId) return false;
        return movieId == that.movieId;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (movieId ^ (movieId >>> 32));
        return result;
    }
}
