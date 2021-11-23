package com.tlcn.movieonline.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    String doUpload(MultipartFile params);
}
