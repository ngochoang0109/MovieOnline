package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "video")
@Getter
@Setter
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source")
    private String source;

    @Column(name = "type")
    private String type;

    @Transient
    private MultipartFile file;

    @ManyToMany(mappedBy = "videos")
    private Set<Movie> movies;

    public Video(String source, Set<Movie> movies) {
        this.source = source;
        this.movies = movies;
    }
}
