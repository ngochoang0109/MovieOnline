package com.tlcn.movieonline.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tlcn.movieonline.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;
    @Override
    public String doUpload(MultipartFile params) {
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
