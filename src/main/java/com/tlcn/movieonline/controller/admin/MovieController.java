package com.tlcn.movieonline.controller.admin;

import com.tlcn.movieonline.dto.admin.MovieDTO;
import com.tlcn.movieonline.model.*;
import com.tlcn.movieonline.service.*;
import com.tlcn.movieonline.validator.AddMovieForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class MovieController {

    @Autowired
    private MovieVideoService movieVideoService;

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

    @Autowired
    private AddMovieForm addMovieForm;

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.setValidator(addMovieForm);
//    }


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
        model.addAttribute("movieRequest", new MovieDTO());
        model.addAttribute("casts", castService.getAllCast());
        model.addAttribute("directors",directorService.getAllDirector());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("countries", countryService.findAll());
        return "admin/movie/movie-add";
    }

    @PostMapping(value = "/movies/add")
    public String add( @Valid @ModelAttribute("movieRequest") MovieDTO movieDTO,
                       BindingResult bindingResult){
        Movie movie=movieService.convertMovieDTOToMovie(movieDTO);
        if (!bindingResult.hasErrors()){
            List<Video> videos= new ArrayList<>();
            Video videoTrailer= new Video(movieDTO.getVideoTrailer(), "trailer");
            String urlMovie= awsS3Service.uploadFile(movieDTO.getVideoMovie());
            Video videoMovie= new Video(urlMovie, "movie");
            videos.add(videoMovie);
            videos.add(videoTrailer);

            movieVideoService.addOneMovieMultiVideo(movie, videos, movieDTO.getCurrent());
            return "redirect:/admin/movies";

        }
        return "redirect:/admin/movies/add";
    }


    @GetMapping("/movies/series")
    public String getMovieSeries(Model model){
        List<Movie> movies= movieService.getMovieSeries();
        model.addAttribute("movies", movies);
        return "admin/movie/movie-series";
    }

    @GetMapping("movies/series/add/{id}")
    public String addMovieSeries(@PathVariable("id") int id,Model model){
        model.addAttribute("movieRequest", new MovieDTO());
        Movie movie= movieService.getMovieById((long)id);
        model.addAttribute("movie", movie);
        return "admin/movie/movie-series-add";
    }
    @PostMapping("movies/series/add/{id}")
    public String addMovieSeries(Model model,
                                 @ModelAttribute("movieRequest") MovieDTO movieDTO,
                                 @PathVariable("id") long id,
                                 BindingResult bindingResult) throws CloneNotSupportedException {


        Movie m= movieService.convertMovieToMovieAnother(movieDTO,id);
        Movie movie= movieService.getMovieById(id);
        if (!bindingResult.hasErrors()){
            List<MovieVideo> movieVideo= (List<MovieVideo>) movie.getMovieVideos();
            String trailer="";
            for (MovieVideo mv: movieVideo) {
                if (mv.getVideo().getType().equals("trailer")){
                    trailer=mv.getVideo().getSource();
                    break;
                }
            }
            List<Video> videos= new ArrayList<>();
            Video videoTrailer= new Video(trailer, "trailer");
            String urlMovie= awsS3Service.uploadFile(movieDTO.getVideoMovie());
            Video videoMovie= new Video(urlMovie, "movie");
            videos.add(videoMovie);
            videos.add(videoTrailer);
            movieVideoService.addOneMovieMultiVideo(m, videos, movieDTO.getCurrent());
            return "redirect:/admin/movies/series";

        }
        return "redirect:movies/series/add/"+id;
    }

    @RequestMapping(value = "movies/disable/{id}")
    public String disableMovie(@PathVariable("id") long id){
        int number=movieService.disableMovie(id);
        if (number==1){
            return "redirect:/admin/movies";
        }
        return "redirect:/admin/movies/series";
    }

}
