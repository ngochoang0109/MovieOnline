package com.tlcn.movieonline.service;

import com.tlcn.movieonline.dto.RegisterRequest;
import com.tlcn.movieonline.model.User;

public interface UserService {
    User registerAccount(RegisterRequest registerRequest);
    Boolean emailExist(String email);
    User getUserByEmail(String email);
}
