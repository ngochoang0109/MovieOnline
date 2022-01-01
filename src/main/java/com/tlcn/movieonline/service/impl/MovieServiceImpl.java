package com.tlcn.movieonline.service.impl;

import com.tlcn.movieonline.constant.MovieConstant;
import com.tlcn.movieonline.dto.MovieDetailResponse;
import com.tlcn.movieonline.dto.MovieResponse;
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

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

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
    private CastService castService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private MovieVideoService movieVideoService;

    @Autowired
    private UserMovieService userMovieService;

    @Override
    public Page<Movie> findAll(int currentPage) {
        Sort sort= Sort.by("title").ascending();
        Pageable pageable= PageRequest.of(currentPage-1,sizePage-2, sort);
        return movieRepository.findAll(pageable);
    }

    @Override
    public List<List<Movie>> findMoviesByGenreTenLimit() {
        List<String> lstGenre= Arrays.asList(MovieConstant.THEATERS_MOVIE,
                MovieConstant.TV_SERIES_MOVIE,MovieConstant.CARTOON_MOVIE);
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


    @Override
    public MovieDetailResponse getMovieDetails(long id) {
        Movie movie=this.getMovieById(id);

        MovieDetailResponse movieDetail= new MovieDetailResponse();
        movieDetail.setId(movie.getId());
        movieDetail.setDescription(movie.getDescription());
        movieDetail.setDuration(movie.getDuration());
        movieDetail.setReleaseYear(movie.getReleaseYear());
        movieDetail.setTitle(movie.getTitle());
        movieDetail.setView(movie.getView());

        for (Image item: movie.getImages()) {
            if (item.getType().equals("poster")){
                movieDetail.setImg(item.getSource());
                break;
            }
        }

        StringJoiner joinerDirector= new StringJoiner(", ");
        for (Director d:movie.getDirectors()) {
            joinerDirector.add(d.getName());
        }
        movieDetail.setDirector(joinerDirector.toString());

        StringJoiner joinerCast= new StringJoiner(", ");
        for (Cast c:movie.getCasts()) {
            joinerCast.add(c.getName());
        }
        movieDetail.setCast(joinerCast.toString());

        StringJoiner joinerGenre= new StringJoiner(", ");
        for (Genre g:movie.getGenres()) {
            joinerGenre.add(g.getName());
        }
        movieDetail.setGenre(joinerGenre.toString());

        StringJoiner joinerCountry= new StringJoiner(", ");
        for (Country c:movie.getCountries()) {
            joinerCountry.add(c.getName());
        }
        movieDetail.setCountry(joinerCountry.toString());


        String video="";
        for (MovieVideo movieVideo: movie.getMovieVideos()) {
            if (movieVideo.getVideo().getType().equals("trailer")){
                video=movieVideo.getVideo().getSource();
                break;
            }
        }
        movieDetail.setTrailer(video);
        List<Float> rating= userMovieService.calculatorRating(movie);
        movieDetail.setRating(rating.get(0));
        movieDetail.setTotalRating(Math.round(rating.get(1)));

        movieDetail.setNumber(movie.getNumber());
        movieDetail.setCurrentEpisode(movieVideoService.getMaxCurrentEpisode(movie.getId()));
        return movieDetail;
    }

    @Override
    public List<Movie> getMovieRelate(long id) {
        Movie movie=this.getMovieById(id);
        int randomGenre= (int) (Math.random()*(movie.getGenres().size()-0));
        List<Genre> lstGenre= (List<Genre>) movie.getGenres();
        List<Movie> lstMovie= new LinkedList<>();
        try {
            lstMovie=movieRepository.findMoviesByGenre(lstGenre.get(randomGenre).getName());
        }
        catch (Exception e){

        }
        finally {

                Collections.sort(lstMovie, new Comparator<Movie>() {
                    @Override
                    public int compare(Movie o1, Movie o2) {
                        return o1.getCreateDate().compareTo(o2.getCreateDate());
                    }
                });
                Collections.reverse(lstMovie);
                lstMovie.stream().limit(20);
        }
        return lstMovie;
    }

    @Override
    public List<Movie> getMoviesByName(String name) {
        return movieRepository.searchByTitleLike(name);
    }

    @Override
    public List<Movie> searchByGenreCountryAndYear(String genre, String country, int year) {
        Genre g= genreService.getGenreByName(genre);
        List<Movie> movies= (List<Movie>) g.getMovies();
        List<Movie> result=new LinkedList<>();
        for (Movie m: movies){
            for (Country c:m.getCountries()) {
                if (c.getName().equals(country)) {
                    result.add(m);
                }
            }
        }
        result=result.stream()
                .filter(movie->movie.getReleaseYear()==year).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Movie> getMovieSeries() {
        List<Movie> movies=movieRepository.getMovieSeries();
        List<Movie> unique= this.getMovieMaxEpisodeAndUniqueTitle(movies);
        Collections.sort(unique,new Comparator<Movie>(){
            @Override
            public int compare(Movie obj1, Movie obj2){
                return  obj1.getCreateDate().compareTo(obj2.getCreateDate());
            }
        });
        return unique;
    }

    @Override
    public Movie convertMovieToMovieAnother(MovieDTO movieDTO, long id) {
        Movie movie=this.getMovieById(id);
        Movie m= new Movie();
        m.setView(0);

        List<Cast> casts= (List<Cast>) movie.getCasts();
        Set<Cast> lstCast=new HashSet<Cast>();
        for (Cast cast: casts) {
            Cast[] arrCast = castService.getCastsByName(cast.getName());
            if (arrCast.length==0){
                Cast c = new Cast();
                c.setName(cast.getName().trim());
                lstCast.add(c);
            }
            else {
                lstCast.add(arrCast[0]);
            }
        }
        List<Director> directors= (List<Director>) movie.getDirectors();
        Set<Director> lstDirector= new HashSet<Director>();
        for (Director item: directors) {
            Director[] arrDirector = directorService.getDirectorsByName(item.getName());
            if (arrDirector.length==0){
                Director d= new Director();
                d.setName(item.getName());
                lstDirector.add(d);
            }else {
                lstDirector.add(arrDirector[0]);
            }
        }
        List<Country> countries= (List<Country>) movie.getCountries();
        Set<Country> lstCountries= new HashSet<>();
        for (Country item: countries) {
            Country[] arrCountry = countryService.getCountriesByName(item.getName().trim());
            if (arrCountry.length==0){
                Country c= new Country();
                c.setName(item.getName());
                lstCountries.add(c);
            }
            else {
                lstCountries.add(arrCountry[0]);
            }
        }
        List<Genre> genres= (List<Genre>) movie.getGenres();
        Set<Genre> lstGenre= new HashSet<>();
        for (Genre item: genres) {
            Genre[] arrGenre = genreService.getGenresByName(item.getName().trim());
            if (arrGenre.length==0){
                Genre g= new Genre();
                g.setName(item.getName().trim());
                lstGenre.add(g);
            }
            else {
                lstGenre.add(arrGenre[0]);
            }
        }
        m.setCasts(lstCast);
        m.setCountries(lstCountries);
        m.setCreateDate(movieDTO.getCreateDate());
        m.setDescription(movie.getDescription());
        m.setDuration(movieDTO.getDuration());
        m.setDirectors(lstDirector);
        m.setGenres(lstGenre);
        Image imgPoster= new Image();
        Image imgBanner= new Image();
        List<Image> lstImage= new LinkedList<>();
        for (Image image:movie.getImages()) {
            if (image.getType().equals("poster")){
                imgPoster.setSource(image.getSource());
                imgPoster.setType(image.getType());
                lstImage.add(imgPoster);
            }
            if (image.getType().equals("banner")){
                imgBanner.setSource(image.getSource());
                imgBanner.setType(image.getSource());
                lstImage.add(imgBanner);
            }
        }
        m.setImages(lstImage);
        m.setNumber(movie.getNumber());
        m.setReleaseYear(movie.getReleaseYear());
        m.setStatus(movie.isStatus());
        m.setTitle(movie.getTitle());
        return m;
    }

    @Override
    public List<Movie> getMovieMaxEpisodeAndUniqueTitle(List<Movie> movies) {
        List<MovieVideo> movieVideos=new LinkedList<>();
        for (Movie m:movies) {
            List<MovieVideo> mv= (List<MovieVideo>) m.getMovieVideos();
            movieVideos.add(mv.get(0));
        }
        // sort
        Collections.sort(movieVideos,new Comparator<MovieVideo>(){
            @Override
            public int compare(MovieVideo obj1, MovieVideo obj2){
                return (int) obj1.getCurrent()- (int) obj2.getCurrent();
            }
        });
        Collections.reverse(movieVideos);
        List<Movie> lstMovie= new LinkedList<>();
        for (MovieVideo m :movieVideos) {
            lstMovie.add(m.getMovie());
        }
        List<Movie> unique = lstMovie.stream()
                .collect(collectingAndThen(toCollection(() ->
                        new TreeSet<>(Comparator.comparing(Movie::getTitle))), ArrayList::new));
        return unique;
    }
}
