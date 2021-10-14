package com.tlcn.movieonline.dto;

import lombok.Getter;
import lombok.Setter;


import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class CommentRequest {

    private String content;
    private Timestamp createDate;
    private Long movieId;
    private Long parentId;

    public CommentRequest(){
        Date date= new Date();
        long time= date.getTime();
        this.createDate= new Timestamp(time);
    }
}
