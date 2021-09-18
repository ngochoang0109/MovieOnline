package com.tlcn.movieonline.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public Cloudinary cloudinary(){
        Cloudinary c= new Cloudinary(ObjectUtils.asMap(
           "cloud_name","drmy6gdet",
                "api_key","631551962945422",
                "api_secret","zpXLnTNc4D0bqqh7rquGgvPtCv4",
                "secure", true
        ));
        return c;
    }


}
