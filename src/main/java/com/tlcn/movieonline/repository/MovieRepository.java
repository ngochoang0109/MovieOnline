package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.Genre;
import com.tlcn.movieonline.model.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

    Movie getMovieById(Long id);

    @Query(
            "select movie  from Movie movie inner join movie.genres g " +
                    "where g.name like %:genre%"
    )
    List<Movie> findMoviesByGenre(@Param("genre") String genre);

    @Query(
            "select movie from Movie movie order by movie.id desc"
    )
    List<Movie> getAll(Pageable pageable);



}
