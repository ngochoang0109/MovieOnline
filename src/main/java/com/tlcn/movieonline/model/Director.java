package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "director")
@Getter
@Setter
@NoArgsConstructor
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "directors")
    private Set<Movie> movies;

    @ManyToMany
    @JoinTable(
            name = "directorycast",
            joinColumns = @JoinColumn(name = "directoryid"),
            inverseJoinColumns = @JoinColumn(name = "castid")
    )
    private Set<Image> images;
}
