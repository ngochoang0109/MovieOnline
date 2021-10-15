package com.tlcn.movieonline.dto;

import com.tlcn.movieonline.model.Comment;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CommentResponse {
    private String content;
    private String username;
    private Timestamp createDate;
    private Long parentId;
    private Long movieId;

    public CommentResponse(String content, String username, Timestamp createDate, Long parentId) {
        this.content = content;
        this.username = username;
        this.createDate = createDate;
        this.parentId=parentId;
    }

    public CommentResponse(String content, String username, Timestamp createDate, Long parentId, Long movieId) {
        this.content = content;
        this.username = username;
        this.createDate = createDate;
        this.parentId = parentId;
        this.movieId = movieId;
    }
}
