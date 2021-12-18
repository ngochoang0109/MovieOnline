package com.tlcn.movieonline.service;

import com.tlcn.movieonline.dto.MovieDetailResponse;
import com.tlcn.movieonline.dto.admin.MovieDTO;
import com.tlcn.movieonline.model.Movie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {
    Page<Movie> findAll(int currentPage);
    List<List<Movie>> findMoviesByGenreTenLimit();
    Movie convertMovieDTOToMovie(MovieDTO movieDTO);
    Movie addMove(Movie movie);
    Movie getMovieById(Long id);
    List<Movie> getMyList(String email);
    List<Movie> getMovieByGenre(String genre);
    Movie countView(long id);
    String getSourceVideoByMovieId(long id);
    MovieDetailResponse getMovieDetails(long id);
    List<Movie> getMovieRelate(long id);
}
