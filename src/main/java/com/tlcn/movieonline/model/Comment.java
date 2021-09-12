package com.tlcn.movieonline.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "parentid")
    private Comment parentId;

    @ManyToOne
    @JoinColumn(name = "movieid")
    private Movie movie;

    @Column(name = "content")
    private String content;

    @Column(name = "createdate")
    private Timestamp createDate;

}
