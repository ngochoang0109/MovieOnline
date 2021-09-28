package com.tlcn.movieonline.controller.admin.api;

import com.tlcn.movieonline.model.ReleaseYear;
import com.tlcn.movieonline.service.YearReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping(value = "/admin/api")
@Controller
public class YearReleaseApiController {

    @Autowired
    private YearReleaseService YearReleaseService;

//    @PostMapping(value = "/YearRelease/add")
//    public @ResponseBody YearRelease addYearRelease(@RequestBody YearRelease g, Model model) {
//        System.out.println(g.getYear());
//        model.addAttribute("YearRelease", g);
//        YearRelease YearRelease=YearReleaseService.addYearRelease(g);
//        return YearRelease;
//    }

    @PostMapping("/YearRelease/add")
    public String addYearRelease(ReleaseYear g, Model model) {

        model.addAttribute("YearRelease", g);
        ReleaseYear YearRelease=YearReleaseService.addYearRelease(g);
        return "redirect:/admin/release-year-manager";
    }

    @PutMapping(value = "/YearRelease/edit")
    public @ResponseBody ReleaseYear editYearRelease(@RequestBody ReleaseYear g){
        ReleaseYear YearRelease=YearReleaseService.addYearRelease(g);
        return YearRelease;
    }

    @GetMapping(value = "/year-release/edit/{id}")
    public String editYearRelease(@PathVariable("id") Long id, Model model){

        ReleaseYear yearRelease = YearReleaseService.getYearReleaseById(id);
        model.addAttribute("yearRelease", yearRelease);
        ReleaseYear YearRelease= YearReleaseService.getYearReleaseById(id);
        return "redirect:/admin/release-year-manager";
    }

    @GetMapping ("/year-release/delete/{id}")
    public String deleteYearRelease(@PathVariable("id") Long id){

        YearReleaseService.deleteYearReleaseById(id);
        return "redirect:/admin/release-year-manager";
    }

}
