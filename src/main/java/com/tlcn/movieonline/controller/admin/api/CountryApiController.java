package com.tlcn.movieonline.controller.admin.api;

import com.tlcn.movieonline.model.Country;
import com.tlcn.movieonline.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping(value = "/admin/api")
@Controller
public class CountryApiController {

    @Autowired
    private CountryService CountryService;

//    @PostMapping(value = "/Country/add")
//    public @ResponseBody Country addCountry(@RequestBody Country g) {
//        Country Country=CountryService.addCountry(g);
//        return Country;
//    }

    @PostMapping("/country/add")
    public String addYearRelease(Country country, Model model) {

        model.addAttribute("country", country);
        CountryService.addCountry(country);
        return "redirect:/admin/country-manager";
    }

    @PutMapping(value = "/Country/edit")
    public @ResponseBody Country editCountry(@RequestBody Country g){
        Country Country=CountryService.addCountry(g);
        return Country;
    }

    @GetMapping(value = "/Country/edit")
    public @ResponseBody Country editCountry(@RequestParam("id") Long id){
        Country Country= CountryService.getCountryById(id);
        return Country;
    }

    @GetMapping ("/country/delete/{id}")
    public String deleteCountry(@PathVariable("id") Long id){

        CountryService.deleteCountryById(id);
        return "redirect:/admin/country-manager";
    }

}
