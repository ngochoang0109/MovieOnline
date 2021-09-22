package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
