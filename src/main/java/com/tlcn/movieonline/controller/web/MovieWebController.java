package com.tlcn.movieonline.controller.web;


import com.tlcn.movieonline.dto.MovieDetailResponse;
import com.tlcn.movieonline.dto.MovieResponse;
import com.tlcn.movieonline.dto.SearchRequest;
import com.tlcn.movieonline.model.*;
import com.tlcn.movieonline.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class MovieWebController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ParentCommentService parentCommentService;

    @Autowired
    private UserMovieService userMovieService;

    @Autowired
    private UserService userService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private CountryService countryService;



    @GetMapping(value = "/home/movie")
    public String movieDetailDefault(@RequestParam(value = "id") Long id,
                                     Model model,
                                     Principal principal){
        return movieDetail(1,id, model, principal);
    }


    @GetMapping("/home/movie/{page}")
    public String movieDetail(@PathVariable("page") int page,
                              @RequestParam(value = "id") Long id,
                              Model model, Principal principal){
        Page<ParentComment> parentCommentPage= parentCommentService.getParentCommentByMovieId(id, page);
        List<ParentComment> lstParentComment= parentCommentPage.getLstData();

        MovieDetailResponse movieDetail= movieService.getMovieDetails(id);

        List<Movie> moviesRelate=movieService.getMovieRelate(id);
        if (moviesRelate.size()!=0){
            List<Movie> renderMovie= moviesRelate.stream().filter(m->m.isStatus()==true).collect(Collectors.toList());
            model.addAttribute("moviesRelate", renderMovie);
        }
        model.addAttribute("lstParentComment", lstParentComment);
        model.addAttribute("movie", movieDetail);
        model.addAttribute("currentPage", page);
        int totalPage=0;
        if (parentCommentPage.getTotalPage()==0){
            totalPage=1;
        }
        else {
            totalPage=parentCommentPage.getTotalPage();
        }
        model.addAttribute("totalPage",totalPage );


        return "web/movie/movie-details";
    }

    @GetMapping("/home/movie/watch")
    public String getMovieWatch(@RequestParam("id") long id,@RequestParam("current") long current, Model model, Principal principal){
        return movieWatch(1,id,current, model, principal);
    }

    @GetMapping("/home/movie/watch/{page}")
    public String movieWatch(@PathVariable("page") int page,
                             @RequestParam("id") long id,
                             @RequestParam("current") long current,
                             Model model, Principal principal){



        Movie movie= movieService.countView(movieService.getMovieIdByMovieId(id, current));
        User user= userService.getUserByEmail(principal.getName());

        List<String> genre= new LinkedList<>();
        String banner="";

        movie.getGenres().stream().forEach(item->genre.add(item.getName()));
        for (Image item: movie.getImages()) {
            if (item.getType().equals("banner")){
                banner=item.getSource();
            }
        }

        UserMovie userMovie= userMovieService.getUserMovieByUserAndMovie(user,movie);
        if (userMovie==null){
            userMovie=userMovieService.add(user,movie);
        }

        MovieResponse movieResponse=
                new MovieResponse(genre, movie.getDescription(),
                        movie.getTitle(), movie.getId(), movie.getView(),banner, userMovie.getRate());



        Page<ParentComment> parentCommentPage= parentCommentService.getParentCommentByMovieId(movieService.getMovieIdByMovieId(id, current), page);
        List<ParentComment> lstParentComment= parentCommentPage.getLstData();

        List<Movie> moviesRelate=movieService.getMovieRelate(movieService.getMovieIdByMovieId(id, current));
        if (moviesRelate.size()!=0){
            List<Movie> renderMovie= moviesRelate.stream().filter(m->m.isStatus()==true).collect(Collectors.toList());
            model.addAttribute("moviesRelate", renderMovie);
        }

        model.addAttribute("movie", movieResponse);
        model.addAttribute("lstParentComment", lstParentComment);
        model.addAttribute("currentPage", page);
        int totalPage=0;
        if (parentCommentPage.getTotalPage()==0){
            totalPage=1;
        }
        else {
            totalPage=parentCommentPage.getTotalPage();
        }
        model.addAttribute("totalPage",totalPage );
        return "web/movie/movie-watch";
    }

    @GetMapping("/movies/search-genre/{genre}")
    public String searchByGenre(@PathVariable("genre") String genre, Model model){
        List<Movie> movies= movieService.getMovieByGenre(genre);
        List<Movie> renderMovie= movies.stream().filter(m->m.isStatus()==true).collect(Collectors.toList());
        List<Movie> lstMovie= movieService.findAll(1).getContent();
        List<Movie> listMovie= lstMovie.stream().filter(m->m.isStatus()==true).collect(Collectors.toList());
        List<Genre> lstGenre=genreService.findAll();
        List<Country> lstCountry=countryService.findAll();
        model.addAttribute("movies", renderMovie);
        model.addAttribute("searchRequest",new SearchRequest());
        model.addAttribute("lstMovie", listMovie);
        model.addAttribute("lstGenre", lstGenre);
        model.addAttribute("lstCountry", lstCountry);
        return "/web/movie/search-movie";
    }

    @GetMapping("/movies/search-contry/{country}")
    public String searchByCountry(@PathVariable("country") String country, Model model){
        List<Movie> movies= movieService.getMoviesByCountry(country);
        List<Movie> renderMovie= movies.stream().filter(m->m.isStatus()==true).collect(Collectors.toList());
        List<Movie> lstMovie= movieService.findAll(1).getContent();
        List<Movie> listMovie= lstMovie.stream().filter(m->m.isStatus()==true).collect(Collectors.toList());
        List<Genre> lstGenre=genreService.findAll();
        List<Country> lstCountry=countryService.findAll();
        model.addAttribute("movies", renderMovie);
        model.addAttribute("searchRequest",new SearchRequest());
        model.addAttribute("lstMovie", listMovie);
        model.addAttribute("lstGenre", lstGenre);
        model.addAttribute("lstCountry", lstCountry);
        return "/web/movie/search-movie";
    }

    @GetMapping("/movies/search")
    public String searchMovie(Model model){
        List<Movie> lstMovie= movieService.findAll(1).getContent();
        List<Movie> listMovie= lstMovie.stream().filter(m->m.isStatus()==true).collect(Collectors.toList());
        List<Genre> lstGenre=genreService.findAll();
        List<Country> lstCountry=countryService.findAll();

        model.addAttribute("searchRequest",new SearchRequest());
        model.addAttribute("lstMovie", listMovie);
        model.addAttribute("lstGenre", lstGenre);
        model.addAttribute("lstCountry", lstCountry);
        return "/web/movie/search-movie";
    }

    @PostMapping("movies/search")
    public String searchMovie(@ModelAttribute("searchRequest") SearchRequest searchRequest, Model model){
        if (searchRequest.getName()!=""){
            List<Movie> movies=movieService.getMoviesByName(searchRequest.getName());
            List<Movie> renderMovie= movies.stream().filter(m->m.isStatus()==true).collect(Collectors.toList());
            model.addAttribute("movies", renderMovie);
        }else {
            List<Movie> movies= movieService.searchByGenreCountryAndYear(searchRequest.getGenre(),
                    searchRequest.getCountry(), searchRequest.getYear());
            List<Movie> renderMovie= movies.stream().filter(m->m.isStatus()==true).collect(Collectors.toList());
            model.addAttribute("movies", renderMovie);
        }
        List<Movie> lstMovie= movieService.findAll(1).getContent();
        List<Movie> listMovie= lstMovie.stream().filter(m->m.isStatus()==true).collect(Collectors.toList());
        List<Genre> lstGenre=genreService.findAll();
        List<Country> lstCountry=countryService.findAll();

        model.addAttribute("searchRequest",new SearchRequest());
        model.addAttribute("lstMovie", listMovie);
        model.addAttribute("lstGenre", lstGenre);
        model.addAttribute("lstCountry", lstCountry);
        return "/web/movie/search-movie";
    }

}
