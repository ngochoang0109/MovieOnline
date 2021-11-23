package com.tlcn.movieonline.service.impl;

import com.tlcn.movieonline.dto.admin.MovieDTO;
import com.tlcn.movieonline.model.*;

import java.util.*;
import java.util.stream.Collectors;

import com.tlcn.movieonline.repository.MovieRepository;
import com.tlcn.movieonline.service.*;
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

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private AwsS3Service awsS3Service;

    @Autowired
    private CastService castService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private GenreService genreService;

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

    @Override
    public Movie convertMovieDTOToMovie(MovieDTO movieDTO) {

        List<Image> images= new LinkedList<>();
        String sourcePoster=cloudinaryService.doUpload(movieDTO.getImagePoster());
        Image imagePoster=new Image(sourcePoster,"poster");
        images.add(imagePoster);
        String sourceWatch= cloudinaryService.doUpload(movieDTO.getImageWatch());
        Image imageBanner=new Image(sourceWatch,"banner");
        images.add(imageBanner);

        String[] strCasts= movieDTO.getCast().trim().split(",");
        Set<Cast> casts= new HashSet<>();
        for (String item: strCasts) {
            Cast[] arrCast = castService.getCastsByName(item.trim());
            if (arrCast.length==0){
                Cast c = new Cast();
                c.setName(item.trim());
                casts.add(c);
            }
            else {
                casts.add(arrCast[0]);
            }
        }


        String[] strDirectors= movieDTO.getDirector().split(",");
        Set<Director> directors= new HashSet<>();
        for (String item: strDirectors) {
            Director[] arrDirector = directorService.getDirectorsByName(item.trim());
            if (arrDirector.length==0){
                Director d= new Director();
                d.setName(item.trim());
                directors.add(d);
            }else {
                directors.add(arrDirector[0]);
            }
        }


        String[] strCountries= movieDTO.getCountry().split(",");
        Set<Country> countries= new HashSet<>();
        for (String item: strCountries) {
            Country[] arrCountry = countryService.getCountriesByName(item.trim());
            if (arrCountry.length==0){
                Country c= new Country();
                c.setName(item.trim());
                countries.add(c);
            }
            else {
                countries.add(arrCountry[0]);
            }
        }


        String[] strGenre= movieDTO.getGenre().trim().split(",");
        Set<Genre> genres= new HashSet<>();
        for (String item: strGenre) {
            Genre[] arrGenre = genreService.getGenresByName(item.trim());
            if (arrGenre.length==0){
                Genre g= new Genre();
                g.setName(item.trim());
                genres.add(g);
            }
            else {
                genres.add(arrGenre[0]);
            }
        }

        Movie movie= new Movie( movieDTO.getTitle(), movieDTO.getDescription(),
                movieDTO.getDuration(), 0, true, movieDTO.getNumber(),
                movieDTO.getReleaseYear(), images, genres, casts, countries, directors);

        return movie;
    }


}
