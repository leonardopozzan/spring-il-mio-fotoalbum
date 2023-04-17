package org.learning.springilmiofotoalbum.controller;

import jakarta.validation.Valid;
import org.learning.springilmiofotoalbum.exception.CategoryNotFoundException;
import org.learning.springilmiofotoalbum.exception.ImageNotFoundException;
import org.learning.springilmiofotoalbum.model.AlertMessage;
import org.learning.springilmiofotoalbum.model.AlertMessage.AlertMessageType;
import org.learning.springilmiofotoalbum.model.Category;
import org.learning.springilmiofotoalbum.model.Image;
import org.learning.springilmiofotoalbum.service.CategoryService;
import org.learning.springilmiofotoalbum.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private CategoryService categoryService;

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
    public String show(Model model,@PathVariable(name = "id") Integer imageId, @RequestParam(name="tryToAddCategory") Optional<Boolean> option, @RequestParam(name="categoryId") Optional<Integer> categoryId){
        try {
            if(option.isPresent()){
                if(categoryId.isPresent()){
                    imageService.addCategoryToImage(imageId, categoryId.get());
                    model.addAttribute("addCategory", false);
                }else{
                    model.addAttribute("addCategory", true);

                }
            }else{
                model.addAttribute("addCategory", false);
            }
            Image image = imageService.getById(imageId);
            model.addAttribute("image" , image);
            model.addAttribute("missingCategories" , categoryService.getMissingCategories(image));
            return "images/show";
        } catch (ImageNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "immagine con id: " + imageId + " non trovata");
        }catch (CategoryNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "catgoria con id: " + categoryId.get() + " non trovata");
        }
    }

//    @PostMapping("/{id}")
//    public String showAddCategory(Model model,@PathVariable(name = "id") Integer imageId,@Valid @ModelAttribute Category category, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            try {
//                Image image = imageService.getById(imageId);
//                model.addAttribute("addCategory", true);
//                model.addAttribute("category" , new Category());
//                model.addAttribute("image" , image);
//                model.addAttribute("missingCategories" , categoryService.getMissingCategories(image));
//                return "images/show";
//            } catch (ImageNotFoundException e){
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "immagine con id: " + imageId + " non trovata");
//            }
//        }
//
//        try {
//            Image image = imageService.addCategoryToImage(imageId, category.getId());
//            model.addAttribute("image" , image);
//            model.addAttribute("addCategory", false);
//            return "images/show";
//        } catch (ImageNotFoundException e){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "immagine con id: " + imageId + " non trovata");
//        }catch (CategoryNotFoundException e){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "catgoria con id: " + category.getId() + " non trovata");
//        }
//    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("image", new Image());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "images/editCreate";
    }

    @PostMapping("create")
    public String doCreate(Model model, @Valid @ModelAttribute("image") Image formImage, BindingResult bindingResult){
        if (!imageService.isValidTitle(formImage))
            bindingResult.addError(new FieldError("image", "title", formImage.getTitle(), false, null, null, "il titolo deve essere unico"));

        if (!imageService.isValidURL(formImage.getUrl()))
            bindingResult.addError(new FieldError("image", "url", formImage.getUrl(), false, null, null, "devi inserire un url valido"));

        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "images/editCreate";
        }
        Image newImage = new Image();
        try{
            newImage = imageService.createImage(formImage);
        } catch (CategoryNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la categoria: "+ e.getMessage() + " non è stata trovata");
        }
        return "redirect:/images/" + Integer.toString(newImage.getId());
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id){
        try {
            Image image = imageService.getById(id);
            model.addAttribute("image" , image);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "images/editCreate";
        } catch (ImageNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "immagine con id: " + id + " non trovata");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(Model model, @Valid @ModelAttribute("image") Image formImage, BindingResult bindingResult, @PathVariable Integer id){
        if (!imageService.isValidTitle(formImage))
            bindingResult.addError(new FieldError("image", "title", formImage.getTitle(), false, null, null, "il titolo deve essere unico"));

        if (!imageService.isValidURL(formImage.getUrl()))
            bindingResult.addError(new FieldError("image", "url", formImage.getUrl(), false, null, null, "devi inserire un url valido"));

        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "images/editCreate";
        }

        Image updatedImage = new Image();
        try {
            updatedImage = imageService.updateImage(formImage, id);
        } catch (ImageNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "immagine con id: " + id + " non è stata trovata");
        }catch (CategoryNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la categoria: "+ e.getMessage() + " non è stata trovata");
        }

        return "redirect:/images/" + Integer.toString(updatedImage.getId());
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Integer id, RedirectAttributes redirectAttributes ){
        try{
            boolean success = imageService.deleteImage(id);
            if (success) {
                AlertMessage alertMessage = new AlertMessage(AlertMessageType.SUCCESS, "immagine con id " + id + " eliminata con succeso");
                redirectAttributes.addFlashAttribute("message", alertMessage);

            } else {
                AlertMessage alertMessage = new AlertMessage(AlertMessageType.ERROR, "Impossibile eliminare la immagine con id " + id);
                redirectAttributes.addFlashAttribute("message", alertMessage);
            }
        }catch (ImageNotFoundException e){
            AlertMessage alertMessage = new AlertMessage(AlertMessageType.ERROR, "immagine con id " + id + " non trovata");
            redirectAttributes.addFlashAttribute("message", alertMessage);
        }
        return "redirect:/images";
    }

    @GetMapping("/{imageId}/remove/{categoryId}")
    public String removeCategoryFromImage(@PathVariable(name = "imageId") Integer imageId, @PathVariable(name = "categoryId") Integer categoryId, RedirectAttributes redirectAttributes){
        try {
            Image image = imageService.getById(imageId);
            if (image.getCategories().size() > 1){
                imageService.removeCategoryFromImage(imageId, categoryId);
                AlertMessage alertMessage = new AlertMessage(AlertMessageType.SUCCESS, "categoria con id " + categoryId + " rimossa con succeso");
                redirectAttributes.addFlashAttribute("message", alertMessage);
            } else {
                AlertMessage alertMessage = new AlertMessage(AlertMessageType.ERROR, "impossibile rimuovere categoria con id " + categoryId + " deve esserci almeno una categoria");
                redirectAttributes.addFlashAttribute("message", alertMessage);
            }
            return "redirect:/images/" + imageId;
        } catch (ImageNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "immagine con id: " + imageId + " non trovata");
        }catch (CategoryNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "catgoria con id: " + categoryId + " non trovata");
        }

    }
}
