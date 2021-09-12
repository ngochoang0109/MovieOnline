package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "yearrelease")
@Getter
@Setter
@NoArgsConstructor
public class YearRelease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year")
    private int year;

    public YearRelease(int year, Set<Movie> movies) {
        this.year = year;
        this.movies = movies;
    }

    @ManyToMany(mappedBy = "yearReleases")
    private Set<Movie> movies;
}
