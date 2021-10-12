package com.tlcn.movieonline.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CommentResponse {
    private String content;
    private String username;
    private Timestamp createDate;
}
