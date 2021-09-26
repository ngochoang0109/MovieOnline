package com.tlcn.movieonline.controller.admin;

import com.tlcn.movieonline.model.YearRelease;
import com.tlcn.movieonline.service.YearReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class ReleaseYearController {

    @Autowired
    YearReleaseService YearReleaseService;

    @RequestMapping(value = "/release-year-manager")
    public String listYearRelease(Model model){
        List<YearRelease> lstYearRelease= YearReleaseService.findAll();

        YearRelease yearRelease = new YearRelease();
        model.addAttribute("yearRelease", yearRelease);
        model.addAttribute("lstYearRelease", lstYearRelease);
                return "admin/year-release-manager";
    }

}
