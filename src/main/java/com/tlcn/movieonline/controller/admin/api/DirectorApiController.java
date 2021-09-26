package com.tlcn.movieonline.controller.admin.api;

import com.tlcn.movieonline.model.Cast;
import com.tlcn.movieonline.model.Director;
import com.tlcn.movieonline.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api")
public class DirectorApiController {
    @Autowired
    private DirectorService directorService;

    @PostMapping(value = "/directors/add")
    public @ResponseBody
    Director add(@RequestBody Director director){
        return directorService.add(director);
    }
}
