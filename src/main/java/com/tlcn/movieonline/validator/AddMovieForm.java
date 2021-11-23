package com.tlcn.movieonline.validator;

import com.tlcn.movieonline.dto.admin.MovieDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tlcn.movieonline.constant.MovieConstant;

@Component
public class AddMovieForm implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return MovieDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MovieDTO movieDTO=(MovieDTO) o;
        if (movieDTO.getTitle().isEmpty()){
            errors.reject("title", MovieConstant.FIELD_NOT_EMPTY);
        }
        if (movieDTO.getNumber()<=0){
            errors.reject("number", MovieConstant.NUMBER_NOT_NAV);
        }
        if (movieDTO.getCurrent()<=0){
            errors.reject("current", MovieConstant.NUMBER_NOT_NAV);
        }
        if (movieDTO.getDuration()<=0){
            errors.reject("duration", MovieConstant.NUMBER_NOT_NAV);
        }
        if (movieDTO.getVideoTrailer().isEmpty()){
            errors.reject("videoTrailer", MovieConstant.FIELD_NOT_EMPTY);
        }
        if (movieDTO.getVideoMovie().equals(null)){
            errors.reject("videoMovie", MovieConstant.FILE_EMPTY);
        }
        if (movieDTO.getImagePoster().equals(null)){
            errors.reject("title", MovieConstant.FILE_EMPTY);
        }
        if (movieDTO.getImagePoster().equals(null)){
            errors.reject("title", MovieConstant.FILE_EMPTY);
        }
        if (movieDTO.getCast().isEmpty()){
            errors.reject("title", MovieConstant.FIELD_NOT_EMPTY);
        }
        if (movieDTO.getDirector().isEmpty()){
            errors.reject("title", MovieConstant.FIELD_NOT_EMPTY);
        }
        if (movieDTO.getGenre().isEmpty()){
            errors.reject("title", MovieConstant.FIELD_NOT_EMPTY);
        }
        if (movieDTO.getCountry().isEmpty()){
            errors.reject("title", MovieConstant.FIELD_NOT_EMPTY);
        }
        if (movieDTO.getDescription().isEmpty()){
            errors.reject("title", MovieConstant.FIELD_NOT_EMPTY);
        }

    }
}
