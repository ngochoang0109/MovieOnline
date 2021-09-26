package com.tlcn.movieonline.controller.admin;

import com.tlcn.movieonline.model.Country;
import com.tlcn.movieonline.model.YearRelease;
import com.tlcn.movieonline.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class CountryController {

    @Autowired
    CountryService CountryService;

    @RequestMapping(value = "/country-manager")
    public String listCountry(Model model){
        List<Country> lstCountry= CountryService.findAll();

        Country country = new Country();
        model.addAttribute("country", country);
        model.addAttribute("lstCountry", lstCountry);
                return "admin/country-manager";
    }

}
