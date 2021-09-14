package com.tlcn.movieonline.controller.web;

import com.tlcn.movieonline.dto.RegisterRequest;
import com.tlcn.movieonline.model.User;
import com.tlcn.movieonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "/web/index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        return "/web/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        RegisterRequest registerRequest= new RegisterRequest();
        model.addAttribute("registerRequest",registerRequest);
        return "/register";
    }

    @RequestMapping(value = "/register", method=RequestMethod.POST)
    public String register(User user, @ModelAttribute RegisterRequest registerRequest, Model model) throws Exception {

        if(userService.emailExist(user.getEmail())){
            model.addAttribute("err", "Email already exists!");
            return "/web/register";
        }
        userService.registerAccount(registerRequest);
        return "/web/index";
    }

}
