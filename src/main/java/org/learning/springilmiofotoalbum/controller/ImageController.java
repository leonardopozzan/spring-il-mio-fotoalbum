package org.learning.springilmiofotoalbum.controller;

import org.learning.springilmiofotoalbum.exception.ImageNotFoundException;
import org.learning.springilmiofotoalbum.model.Image;
import org.learning.springilmiofotoalbum.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/images")
public class ImageController {
    @Autowired
    ImageService imageService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "title") Optional<String> keyword){
        List<Image> images = new ArrayList<>();
        if (keyword.isEmpty()){
            images = imageService.getAllImages();
        } else{
            images = imageService.getFilteredImages(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("images", images);
        return "images/index";
    }

    @GetMapping("/{id}")
    public String show(Model model,@PathVariable Integer id){
        try {
            Image image = imageService.getById(id);
            model.addAttribute("image" , image);
            return "images/show";
        } catch (ImageNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "immagine con id: " + id + " non è stata trovata");
        }
    }
}
