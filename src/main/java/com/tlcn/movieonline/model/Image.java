package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "image")
@Getter
@Setter
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source")
    private String suorce;

    @ManyToMany(mappedBy = "images")
    private Set<Movie> movies;

    @ManyToMany(mappedBy = "images")
    private Set<Cast> casts;

    @ManyToMany(mappedBy = "images")
    private Set<Director> directors;
}
