package com.tlcn.movieonline.controller.web.api;

import com.tlcn.movieonline.service.AwsS3Service;
import com.tlcn.movieonline.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class MovieApiWebController {

    @Autowired
    private AwsS3Service awsS3Service;

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/stream/video/{id}", method = RequestMethod.GET)
    public Mono<ResponseEntity<byte[]>> streamVideo(@RequestHeader("Range") HttpHeaders header, @PathVariable("id") long id) {
        String source= movieService.getSourceVideoByMovieId(id);
        return Mono.just(awsS3Service.getVideoBytes(source));
    }

}
