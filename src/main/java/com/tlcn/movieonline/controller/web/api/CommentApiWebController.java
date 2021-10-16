package com.tlcn.movieonline.controller.web.api;

import com.tlcn.movieonline.dto.CommentRequest;
import com.tlcn.movieonline.dto.CommentResponse;
import com.tlcn.movieonline.model.Comment;
import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.model.User;
import com.tlcn.movieonline.model.UserMovie;
import com.tlcn.movieonline.service.CommentService;

import com.tlcn.movieonline.service.MovieService;
import com.tlcn.movieonline.service.UserMovieService;
import com.tlcn.movieonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping(value = "/home/api")
public class CommentApiWebController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserMovieService userMovieService;

    @PostMapping(value = "/add-comment")
    public @ResponseBody CommentResponse addComment(@RequestBody CommentRequest commentRequest, Principal principal){
        Comment comment=commentService.addComment(commentRequest, principal);

        CommentResponse commentResponse =
                new CommentResponse(comment.getContent(),
                                    comment.getUser().getName(),
                                    comment.getCreateDate(),
                                    comment.getParentComment().getId(),
                                    comment.getMovie().getId());
        return commentResponse;
    }

}
