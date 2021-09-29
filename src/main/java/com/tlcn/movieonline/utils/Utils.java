package com.tlcn.movieonline.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public class Utils {

    @Autowired
    static Cloudinary cloudinary;

    public static String doUpload(MultipartFile params){
        try{
            Map jsonResult= cloudinary.uploader().uploadLarge(params.getBytes(),
                    ObjectUtils.asMap("resource_type","auto","chunk_size",100000000));
            String url=(String) jsonResult.get("secure_url");
            return url;
        }
        catch (Exception e){
            return null;
        }
    }
}
