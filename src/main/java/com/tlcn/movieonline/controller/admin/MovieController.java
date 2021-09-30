package com.tlcn.movieonline.controller.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tlcn.movieonline.dto.MovieRequest;
import com.tlcn.movieonline.model.*;
import com.tlcn.movieonline.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping(value = "/movies")
    public String getAllMovie(Model model){
        List<Movie> lstMovie= movieService.getAll();
        model.addAttribute("lstMovie", lstMovie);
        return "admin/movie-manager";
    }

    @GetMapping(value = "/movies/add")
    public String add(Model model){
        model.addAttribute("movieRequest", new MovieRequest());
        return "admin/movie-add";
    }

    @PostMapping(value = "/movies/add")
    public String add(@ModelAttribute("movieRequest") MovieRequest movieRequest){
        ReleaseYear releaseYear= new ReleaseYear();
        Movie movie= new Movie();
        Image image= new Image();
        Video video= new Video();

        String urlImage=doUpload(movieRequest.getImage());
        if (urlImage!=""){
            image.setSource(urlImage);
        }
        Set<Image> images= new HashSet<>();
        images.add(image);

        String urlVideoTrailer=doUpload(movieRequest.getVideoTrailer());
        if (urlVideoTrailer!=""){
            video.setSource(urlVideoTrailer);
        }
        Set<Video> videos= new HashSet<>();
        videos.add(video);

        String[] strCasts= movieRequest.getCast().split(",");
        Set<Cast> casts= new HashSet<>();
        for (String item: strCasts) {
            Cast cast= new Cast();
            cast.setName(item);
            casts.add(cast);
        }

        String[] strDirectors= movieRequest.getDirector().split(",");
        Set<Director> directors= new HashSet<>();
        for (String item: strDirectors) {
            Director director= new Director();
            director.setName(item);
            directors.add(director);
        }



        String[] strCountries= movieRequest.getCountry().split(",");
        Set<Country> countries= new HashSet<>();
        for (String item: strCountries) {
            Country country= new Country();
            country.setName(item);
            countries.add(country);
        }

        String[] strGenre= movieRequest.getGenre().split(",");
        Set<Genre> genres= new HashSet<>();
        for (String item: strGenre) {
            Genre genre= new Genre();
            genre.setName(item);
            genres.add(genre);
        }


        //do something

        releaseYear.setYear(movieRequest.getReleaseYear());
        movie.setTitle(movieRequest.getTitle());
        movie.setDescription(movieRequest.getDescription());
        movie.setDuration(movieRequest.getDuration());
        movie.setNumber(movieRequest.getNumber());
        movie.setView(0);
        movie.setStatus(movieRequest.isStatus());
        movie.setRelYearId(releaseYear);
        movie.setImages(images);
        movie.setVideos(videos);
        movie.setCasts(casts);
        movie.setDirectors(directors);
        movie.setCountries(countries);
        movie.setGenres(genres);

        movieService.addMove(movie);

        return "admin/movie-manager";
    }

    public String doUpload(MultipartFile params){
        String url="";
        try{
            Map jsonResult= cloudinary.uploader().uploadLarge(params.getBytes(),
                    ObjectUtils.asMap("resource_type","auto","chunk_size",100000000));
            url=(String) jsonResult.get("secure_url");
            return url;
        }
        catch (Exception e){
            return url;
        }
    }



}
