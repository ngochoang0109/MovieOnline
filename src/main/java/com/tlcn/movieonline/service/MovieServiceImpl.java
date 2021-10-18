package com.tlcn.movieonline.service;

import com.tlcn.movieonline.model.Comment;
import com.tlcn.movieonline.model.Genre;
import com.tlcn.movieonline.model.Movie;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import com.tlcn.movieonline.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<List<Movie>> findMoviesByGenreTenLimit() {
        List<String> lstGenre= Arrays.asList("Chiếu rạp","Truyền hình","Hoạt hình");
        List<List<Movie>> lstMovie= new ArrayList<>();
        List<Movie> lstNewMovie= movieRepository.findAll();
        Collections.reverse(lstNewMovie);
        for (String item: lstGenre) {
            List<Movie> temp=movieRepository.findMoviesByGenreTenLimit(item);
            Collections.sort(temp,new Comparator<Movie>(){
                @Override
                public int compare(Movie obj1, Movie obj2){
                    return obj1.getView()- obj2.getView();
                }
            });
            Collections.reverse(temp);
            List<Movie> result= temp.stream().limit(10).collect(Collectors.toList());
            lstMovie.add(result);
        }
        lstNewMovie=lstNewMovie.stream().limit(10).collect(Collectors.toList());
        lstMovie.add(lstNewMovie);
        return lstMovie;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Movie addMove(Movie movie) {
        Movie m= movieRepository.save(movie);
        return m;
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.getMovieById(id);
    }



}
