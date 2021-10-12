package com.tlcn.movieonline.controller.web.api;

import com.tlcn.movieonline.dto.CommentRequest;
import com.tlcn.movieonline.dto.CommentResponse;
import com.tlcn.movieonline.model.Comment;
import com.tlcn.movieonline.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/home/api")
public class CommentApiWebController {

    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/add-comment")
    public @ResponseBody
    CommentResponse addComment(@RequestBody CommentRequest commentRequest){
        Comment comment=commentService.addComment(commentRequest);
        CommentResponse commentRespone = new CommentResponse();
        commentRespone.setContent(comment.getContent());
        commentRespone.setUsername("Hoang dep trai");
        commentRespone.setCreateDate(comment.getCreateDate());
        return commentRespone;
    }

}
