package com.tlcn.movieonline.controller.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tlcn.movieonline.model.Movie;
import com.tlcn.movieonline.service.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/stream")
public class MovieApiWebController {

    @Autowired
    private AwsS3Service awsS3Service;

    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public Mono<ResponseEntity<byte[]>> streamVideo(@RequestHeader("Range") HttpHeaders header) {
        return Mono.just(awsS3Service.getVideoBytes("zone447.mp4_2021-10-29T13:49:52.361"));
    }
}
