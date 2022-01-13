package com.tlcn.movieonline.service;

import com.tlcn.movieonline.dto.MovieDetailResponse;
import com.tlcn.movieonline.dto.MovieResponse;
import com.tlcn.movieonline.dto.admin.MovieDTO;
import com.tlcn.movieonline.model.Movie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {
    Page<Movie> findAll(int currentPage);
    List<List<Movie>> findMoviesByGenreTenLimit();
    Movie convertMovieDTOToMovie(MovieDTO movieDTO);
    MovieDTO convertMovieTOMovieDTO(Movie movie);
    Movie addMove(Movie movie);
    Movie getMovieById(Long id);
    List<Movie> getMyList(String email);
    List<Movie> getMovieByGenre(String genre);
    Movie countView(long id);
    String getSourceVideoByMovieId(long id);
    MovieDetailResponse getMovieDetails(long id);
    List<Movie> getMovieRelate(long id);
    List<Movie> getMoviesByName(String name);
    List<Movie> searchByGenreCountryAndYear(String genre, String country, int year);
    List<Movie> getMovieSeries();
    Movie convertMovieToMovieAnother(MovieDTO movieDTO, long id);
    List<Movie> getMovieMaxEpisodeAndUniqueTitle(List<Movie> movie);
    List<Movie> getMoviesByTitle(String title);
    long getMovieIdByMovieId(long id, long current);
    List<Movie> getMoviesByCountry(String country);
    long getMaxCurrent(String title);
    int disableMovie(long id);
}
