package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "releaseYear")
@Getter
@Setter
@NoArgsConstructor
public class ReleaseYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year")
    private int year;

    @OneToMany(mappedBy = "relYearId")
    private Set<Movie> movies;
}
