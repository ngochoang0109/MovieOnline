package com.tlcn.movieonline.service.impl;

import com.tlcn.movieonline.dto.MovieUserResponse;
import com.tlcn.movieonline.model.*;

import java.util.*;
import java.util.stream.Collectors;

import com.tlcn.movieonline.repository.MovieRepository;
import com.tlcn.movieonline.service.MovieService;
import com.tlcn.movieonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.transaction.Transactional;

@Service
public class MovieServiceImpl implements MovieService {

    private final int sizePage=10;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserService userService;


    @Override
    public Page<Movie> findAll(int currentPage) {
        Sort sort= Sort.by("title").ascending();
        Pageable pageable= PageRequest.of(currentPage-1,sizePage-2, sort);
        return movieRepository.findAll(pageable);
    }

    @Override
    public List<List<Movie>> findMoviesByGenreTenLimit() {
        List<String> lstGenre= Arrays.asList("Chiếu rạp","Truyền hình","Hoạt hình");
        List<List<Movie>> lstMovie= new ArrayList<>();
        List<Movie> lstNewMovie= movieRepository.getAll(PageRequest.of(0,sizePage));
        for (String item: lstGenre) {
            List<Movie> temp=movieRepository.findMoviesByGenre(item);
            Collections.sort(temp,new Comparator<Movie>(){
                @Override
                public int compare(Movie obj1, Movie obj2){
                    return (int) obj1.getView()- (int) obj2.getView();
                }
            });
            Collections.reverse(temp);
            List<Movie> result=temp.stream()
                    .filter(m-> m.isStatus()==true)
                    .limit(10).collect(Collectors.toList());
            lstMovie.add(result);
        }
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

    @Override
    public List<Movie> getMyList(String email) {
        User user= userService.getUserByEmail(email);
        List<Movie> movies= new LinkedList<>();
        for (UserMovie userMovie: user.getUserMovies()){
            if(userMovie.getMovie().isStatus()==true){
                movies.add(userMovie.getMovie());
            }
        }
        return movies;
    }

    @Override
    public List<Movie> getMovieByGenre(String genre) {
        List<Movie> movies= movieRepository.findMoviesByGenre(genre);
        return movies;
    }

    @Override
    @Transactional
    @ExceptionHandler(Exception.class)
    public Movie countView(long id) {
        Movie movie= this.getMovieById(id);
        movie.setView(movie.getView()+1);
        return movieRepository.save(movie);
    }

    @Override
    public String getSourceVideoByMovieId(long id) {
        Movie movie= this.getMovieById(id);
        List<MovieVideo> movieVideos= (List<MovieVideo>) movie.getMovieVideos();
        String video="";
        for (MovieVideo item: movieVideos) {
            if (item.getVideo().getType().equals("movie")){
                video=item.getVideo().getSource();
            }
        }
        return video;
    }

}
