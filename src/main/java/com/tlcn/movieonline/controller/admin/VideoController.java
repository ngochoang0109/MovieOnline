package com.tlcn.movieonline.controller.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tlcn.movieonline.model.Video;
import com.tlcn.movieonline.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class VideoController {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private VideoService videoService;

    @GetMapping(value = "/admin/videos")
    public String listVideo(Model model){
        List<Video> lstVideo= new ArrayList<>();
        lstVideo= videoService.getAll();
        model.addAttribute("lstVideo",lstVideo);
        model.addAttribute("video", new Video());
        return "admin/video-manager";
    }

    @PostMapping(value = "/admin/video/add")
    public String addVideo(@ModelAttribute(value = "video") Video video){
        try {
            Map jsonResult=cloudinary.uploader().uploadLarge(video.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type","video",
                            "chunk_size", 6000000));
            String urlVideo=(String) jsonResult.get("secure_url");
            Video v= new Video();
            v.setSource(urlVideo);
            v.setType(video.getType());
            videoService.addVideo(v);
            return "redirect:/admin/videos";
        }
        catch (Exception e){
            return "redirect:/admin/videos";
        }
    }
}
