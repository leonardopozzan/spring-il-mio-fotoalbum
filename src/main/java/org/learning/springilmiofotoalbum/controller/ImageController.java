package org.learning.springilmiofotoalbum.controller;

import jakarta.validation.Valid;
import org.learning.springilmiofotoalbum.exception.ImageNotFoundException;
import org.learning.springilmiofotoalbum.model.Category;
import org.learning.springilmiofotoalbum.model.Image;
import org.learning.springilmiofotoalbum.service.CategoryService;
import org.learning.springilmiofotoalbum.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/images")
public class ImageController {
    @Autowired
    ImageService imageService;

    @Autowired
    CategoryService categoryService;

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

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("image", new Image());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "images/editCreate";
    }

    @PostMapping("create")
    public String doCreate(Model model, @Valid @ModelAttribute("image") Image formImage, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "images/editCreate";
        }
        Image newImage = imageService.createImage(formImage);
        return "redirect:/images/" + Integer.toString(newImage.getId());
    }
}
