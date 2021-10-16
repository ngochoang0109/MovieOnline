package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Comment;
import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.model.ParentComment;
import com.tlcn.movieonline.repository.ParentCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParentCommentServiceImpl implements ParentCommentService{

    @Autowired
    private ParentCommentRepository parentCommentRepository;

    @Autowired
    private MovieService movieService;

    @Override
    public ParentComment add(ParentComment parentComment) {
        return parentCommentRepository.save(parentComment);
    }

    @Override
    public ParentComment getParentCommentById(Long id) {
        return parentCommentRepository.getParentCommentById(id);
    }

    @Override
    public List<ParentComment> getParentCommentByMovieId(Long id) {
        Movie movie= movieService.getMovieById(id);
        List<Comment> lstComment= movie.getComments();
        List<ParentComment> lstParentComment= new LinkedList<>();
        Set<Long> setParentId=new HashSet<>();
        for (Comment item: lstComment) {
            setParentId.add(item.getParentComment().getId());
        }
        for (Long parentId:setParentId){
            ParentComment parentComment= parentCommentRepository.getParentCommentById(parentId);
            lstParentComment.add(parentComment);
        }
        for (ParentComment item:lstParentComment){
            Collections.sort(item.getComments(),new Comparator<Comment>(){
                public int compare(Comment obj01, Comment obj2){
                    return obj01.getCreateDate().compareTo(obj2.getCreateDate());
                }
            });
        }

        return lstParentComment;
    }
}
