package com.tlcn.movieonline.service.impl;

import com.tlcn.movieonline.model.Comment;
import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.model.Page;
import com.tlcn.movieonline.model.ParentComment;
import com.tlcn.movieonline.repository.CommentRepository;
import com.tlcn.movieonline.repository.ParentCommentRepository;
import com.tlcn.movieonline.service.MovieService;
import com.tlcn.movieonline.service.ParentCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParentCommentServiceImpl implements ParentCommentService {

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

    private final int pageSize=3;

    @Override
    public Page<ParentComment> getParentCommentByMovieId(Long id, int currentPage) {
        Movie movie= movieService.getMovieById(id);

        List<Comment> lstComment= (List<Comment>) movie.getComments();

        List<ParentComment> lstParentComment= new LinkedList<>();
        Set<Long> setParentId=new HashSet<>();
        for (Comment item: lstComment) {
            setParentId.add(item.getParentComment().getId());
        }
        for (Long parentId:setParentId){
            ParentComment parentComment= parentCommentRepository.getParentCommentById(parentId);
            lstParentComment.add(parentComment);
        }
        int totalPage=this.totalPage(lstParentComment);
        List<ParentComment> pagingAndSorting=this.paginationAndSortingTime(lstParentComment, currentPage);
        Page<ParentComment> parentComments= new Page<ParentComment>(totalPage, pagingAndSorting);
        return parentComments;
    }

    @Override
    public List<ParentComment> paginationAndSortingTime(List<ParentComment> parentComments, int page) {
        int indexFrom= (page-1)*pageSize;
        int indexTo= indexFrom+pageSize;
        Collections.sort(parentComments,new Comparator<ParentComment>(){
            public int compare(ParentComment obj01, ParentComment obj2){
                return obj01.getComments().get(0).getCreateDate()
                        .compareTo(obj2.getComments().get(0).getCreateDate());
            }
        });
        Collections.reverse(parentComments);
        List<ParentComment> result=parentComments.subList(indexFrom, Math.min(indexTo, parentComments.size()));
        return result;
    }

    @Override
    public int totalPage(List<ParentComment> parentComments) {
        if ((parentComments.size()%pageSize)==0){
            return parentComments.size()/pageSize;
        }
        return parentComments.size()/pageSize+1;
    }
}
