//package com.tlcn.movieonline.controller.admin;
//
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//import com.tlcn.movieonline.service.ImageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping(value = "/admin")
//public class ImageController {
//
//    @Autowired
//    private ImageService imageService;
//
//    @Autowired
//    private Cloudinary cloudinary;
//
//    @GetMapping(value = "/images")
//    public String listImage(Model model){
//        List<Image> lstImage=imageService.getAll();
//        model.addAttribute("lstImage", lstImage);
//        model.addAttribute("image",new Image());
//        return "admin/image-manager";
//    }
//
//    @PostMapping(value = "/image/add")
//    public String addImage(@ModelAttribute(value = "image") Image image){
//        try {
//            Map jsonResult=cloudinary.uploader().upload(image.getFile().getBytes(),
//                    ObjectUtils.asMap("resource_type","image"));
//            String urlImg=(String) jsonResult.get("secure_url");
//            Image i= new Image();
//            i.setSource(urlImg);
//            i.setType(image.getType());
//            imageService.addImage(i);
//            return "redirect:/admin/images";
//        }
//        catch (Exception e){
//            return "redirect:/admin/images";
//        }
//    }
//
//
//}
