package com.tlcn.movieonline.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


public interface AwsS3Service {
    String uploadFile(MultipartFile param);
    ResponseEntity<byte[]> getVideoBytes(String keyName);
}
