package com.tlcn.movieonline.controller.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tlcn.movieonline.dto.MovieRequest;
import com.tlcn.movieonline.model.*;
import com.tlcn.movieonline.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class MovieController {

    @Autowired
    private MovieVideoService movieVideoService;

    @Autowired
    private Cloudinary cloudinary;


    @Autowired
    private CastService castService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private AwsS3Service awsS3Service;

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/movies")
    public String getMovieIndex(Model model){
        return getMoviePage(1, model);
    }

    @GetMapping(value = "/movies/{page}")
    public String getMoviePage(@PathVariable("page") int numberPage, Model model) {

        Page<Movie> page=movieService.findAll(numberPage);
        List<Movie> movies= page.getContent();
        long totalMovie= page.getTotalElements();
        int totalPage=page.getTotalPages();

        model.addAttribute("movies", movies);
        model.addAttribute("currentPage", numberPage);
        model.addAttribute("totalMovie", totalMovie);
        model.addAttribute("totalPage", totalPage);
        return "admin/movie/movie-manager";
    }

    @GetMapping(value = "/movies/add")
    public String add(Model model) {
        model.addAttribute("movieRequest", new MovieRequest());
        return "admin/movie/movie-add";
    }

    @PostMapping(value = "/movies/add")
    public String add(@ModelAttribute("movieRequest") MovieRequest movieRequest){


        List<Image> images= new LinkedList<>();
        String sourcePoster=doUpload(movieRequest.getImagePoster());
        Image imagePoster=new Image(sourcePoster,"poster");
        images.add(imagePoster);
        String sourceWatch= doUpload(movieRequest.getImageWatch());
        Image imageWatch=new Image(sourceWatch,"watch");
        images.add(imageWatch);

        List<Video> videos= new ArrayList<>();
        Video videoTrailer= new Video(movieRequest.getVideoTrailer(), "trailer");
        String urlMovie= awsS3Service.uploadFile(movieRequest.getVideoMovie());
        Video videoMovie= new Video(urlMovie, "movie");
        videos.add(videoMovie);
        videos.add(videoTrailer);

        String[] strCasts= movieRequest.getCast().trim().split(",");
        Set<Cast> casts= new HashSet<>();
        for (String item: strCasts) {
            Cast cast = castService.getCastByName(item.trim());
            if (cast == null) {
                Cast c = new Cast();
                c.setName(item);
                casts.add(c);
            } else {
                casts.add(cast);
            }
        }


        String[] strDirectors= movieRequest.getDirector().split(",");
        Set<Director> directors= new HashSet<>();
        for (String item: strDirectors) {
            Director director = directorService.getDirectorByName(item.trim());
            if (director == null) {
                Director d= new Director();
                d.setName(item);
                directors.add(d);
            }
            else {
                directors.add(director);
            }
        }


        String[] strCountries= movieRequest.getCountry().split(",");
        Set<Country> countries= new HashSet<>();
        for (String item: strCountries) {
            Country country = countryService.getCountryByName(item.trim());
            if (country == null) {
                Country c= new Country();
                c.setName(item);
                countries.add(c);
            }
            else {
                countries.add(country);
            }
        }


        String[] strGenre= movieRequest.getGenre().split(",");
        Set<Genre> genres= new HashSet<>();
        for (String item: strGenre) {
            Genre genre = genreService.getGenreByName(item.trim());
            if (genre == null) {
                Genre g= new Genre();
                g.setName(item);
                genres.add(g);
            }
            else {
                genres.add(genre);
            }
        }

        Movie movie= new Movie( movieRequest.getTitle(), movieRequest.getDescription(),
                                movieRequest.getDuration(), 0, true, movieRequest.getNumber(),
                                 movieRequest.getReleaseYear(), images, genres,
                                casts, countries, directors);

        movieVideoService.addOneMovieMultiVideo(movie, videos, movieRequest.getCurrent());

        return "admin/movie/movie-manager";
    }

    public String doUpload(MultipartFile params){
        String url="";
        try{
            Map jsonResult= cloudinary.uploader().upload(params.getBytes(),
                    ObjectUtils.asMap("resource_type","image"));
            url=(String) jsonResult.get("secure_url");
            return url;
        }
        catch (Exception e){
            return url;
        }
    }
}
