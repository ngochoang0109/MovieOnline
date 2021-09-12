package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cast")
@Getter
@Setter
@NoArgsConstructor
public class Cast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "casts")
    private Set<Movie> movies;

    @ManyToMany
    @JoinTable(
            name = "castimage",
            joinColumns = @JoinColumn(name = "castid"),
            inverseJoinColumns = @JoinColumn(name = "imageid")
    )
    private Set<Image> images;
}
