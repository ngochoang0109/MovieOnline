package com.tlcn.movieonline.service;

import com.tlcn.movieonline.dto.CommentRequest;
import com.tlcn.movieonline.model.*;
import com.tlcn.movieonline.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserMovieService userMovieService;

    @Autowired
    private ParentCommentService parentCommentService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Comment addComment(CommentRequest commentRequest, Principal principal) {
        User user= userService.getUserByEmail(principal.getName());
        Movie movie= movieService.getMovieById(commentRequest.getMovieId());

        Comment comment= new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setCreateDate(commentRequest.getCreateDate());
        comment.setMovie(movie);
        comment.setUser(user);
        if (commentRequest.getParentId()==0){
            // comment to root comment of user.
            ParentComment parentComment= parentCommentService.add(new ParentComment(commentRequest.getParentId()));
            comment.setParentComment(parentComment);
            comment=commentRepository.save(comment);
        }
        else {
            ParentComment parentComment= parentCommentService.getParentCommentById(commentRequest.getParentId());
            comment.setParentComment(parentComment);
            comment=commentRepository.save(comment);
        }
        return comment;
    }

}
