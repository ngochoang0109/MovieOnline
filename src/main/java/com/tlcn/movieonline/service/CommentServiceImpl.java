package com.tlcn.movieonline.service;

import com.tlcn.movieonline.dto.CommentRequest;
import com.tlcn.movieonline.model.Comment;
import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.model.User;
import com.tlcn.movieonline.model.UserMovie;
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

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Comment addComment(CommentRequest commentRequest, Principal principal) {
        Comment comment= new Comment();

        User user= userService.getUserByEmail(principal.getName());
        Movie movie= movieService.getMovieById(commentRequest.getMovieId());
        UserMovie userMovie=userMovieService.add(user, movie);

        comment.setContent(commentRequest.getContent());
        comment.setCreateDate(commentRequest.getCreateDate());
        comment.setMovie(movie);
        System.out.println(commentRepository.getCommentByParentId((long) 1));
//        comment.setParentId(this.getCommentByParentId(commentRequest.getParentId()));

        return commentRepository.save(comment);
    }

    @Override
    public Comment getCommentByParentId(Long parentId) {
        return commentRepository.getCommentByParentId(parentId);
    }
}
