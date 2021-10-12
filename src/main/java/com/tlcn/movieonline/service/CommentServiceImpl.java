package com.tlcn.movieonline.service;

import com.tlcn.movieonline.dto.CommentRequest;
import com.tlcn.movieonline.model.Comment;
import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.model.User;
import com.tlcn.movieonline.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Comment addComment(CommentRequest commentRequest) {
        Comment comment= new Comment();



        Movie movie= movieService.getMovieById(commentRequest.getMovieId());
        comment.setContent(commentRequest.getContent());
        comment.setCreateDate(commentRequest.getCreateDate());
        comment.setMovie(movie);

        Comment c= commentRepository.save(comment);
        return c;
    }

    @Override
    public List<Comment> getAllCommentByProductId(Long id) {
        return null;
    }
}
