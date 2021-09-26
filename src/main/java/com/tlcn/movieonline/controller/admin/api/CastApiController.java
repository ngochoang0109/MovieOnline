package com.tlcn.movieonline.controller.admin.api;

import com.tlcn.movieonline.model.Cast;
import com.tlcn.movieonline.service.CastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/api")
public class CastApiController {
    @Autowired
    private CastService castService;

    @PostMapping(value = "/casts/add")
    public @ResponseBody Cast add(@RequestBody Cast cast){
        return castService.add(cast);
    }
}
