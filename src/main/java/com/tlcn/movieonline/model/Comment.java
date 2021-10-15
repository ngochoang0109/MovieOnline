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
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "createdate")
    private Timestamp createDate;

    @ManyToOne
    @JoinColumn(name = "movieid")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "parentid")
    private ParentComment parentComment;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;


    public Comment(String content, Timestamp createDate, Movie movie){
        this.content= content;
        this.createDate= createDate;
        this.movie= movie;
    }

    public Comment(String content, Timestamp createDate, Movie movie, ParentComment parentComment, User user) {
        this.content = content;
        this.createDate = createDate;
        this.movie = movie;
        this.parentComment = parentComment;
        this.user = user;
    }


}
