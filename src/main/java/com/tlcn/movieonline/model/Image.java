package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Collection;

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
    private String source;

    @Column(name = "type")
    private String type;

    public Image(String source, String type) {
        this.source = source;
        this.type = type;
    }

    @Transient
    private MultipartFile file;

    @ManyToMany(mappedBy = "images")
    private Collection<Movie> movies;
}
