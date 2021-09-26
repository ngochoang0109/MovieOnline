package com.tlcn.movieonline.controller.admin;

import com.tlcn.movieonline.model.Cast;
import com.tlcn.movieonline.service.CastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CastController {
    @Autowired
    private CastService castService;
    @GetMapping("/casts")
    public String lstCast(Model model){
        List<Cast> lstCast= castService.getAllCast();
        model.addAttribute("lstCast", lstCast);
        return "admin/cast-manager";
    }
}
