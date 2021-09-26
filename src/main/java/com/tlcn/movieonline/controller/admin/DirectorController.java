package com.tlcn.movieonline.controller.admin;

import com.tlcn.movieonline.model.Cast;
import com.tlcn.movieonline.model.Director;
import com.tlcn.movieonline.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@RequestMapping(value = "/admin")
public class DirectorController {
    @Autowired
    private DirectorService directorService;
    @GetMapping("/directors")
    public String lstCast(Model model){
        List<Director> lstDirector= directorService.getAllDirector();
        model.addAttribute("lstDirector", lstDirector);
        return "admin/director-manager";
    }
}
