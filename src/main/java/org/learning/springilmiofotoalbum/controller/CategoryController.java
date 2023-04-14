package org.learning.springilmiofotoalbum.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.learning.springilmiofotoalbum.exception.CategoryNotFoundException;
import org.learning.springilmiofotoalbum.exception.ImageNotFoundException;
import org.learning.springilmiofotoalbum.model.AlertMessage;
import org.learning.springilmiofotoalbum.model.Category;
import org.learning.springilmiofotoalbum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("category", new Category());
        return "categories/index";
    }

    @PostMapping("/create")
    public String doCreate(Model model, @Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(!categoryService.isValidName(formCategory)){
            bindingResult.addError(new FieldError("category", "name", formCategory.getName(), false, null, null, "il nome deve essere unico"));
            model.addAttribute("categories", categoryService.getAllCategories());
            return "categories/index";
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "categories/index";
        }
        Category newCategory = categoryService.createCategory(formCategory);
        AlertMessage alertMessage = new AlertMessage(AlertMessage.AlertMessageType.SUCCESS, "categoria con id " + newCategory.getId() + " creata con succeso");
        redirectAttributes.addFlashAttribute("message", alertMessage);
        return "redirect:/categories";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable Integer id){
        try {
            Category category = categoryService.getById(id);
            model.addAttribute("category" , category);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "categories/index";
        } catch (CategoryNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "categoria con id: " + id + " non è stata trovata");
        }
    }

    @PostMapping("edit/{id}")
    public String doEdit(Model model, @PathVariable Integer id, @Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(!categoryService.isValidName(formCategory)){
            bindingResult.addError(new FieldError("category", "name", formCategory.getName(), false, null, null, "il nome deve essere unico"));
            model.addAttribute("categories", categoryService.getAllCategories());
            return "categories/index";
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "categories/index";
        }
        Category newCategory = categoryService.updateCategory(formCategory, id);
        AlertMessage alertMessage = new AlertMessage(AlertMessage.AlertMessageType.SUCCESS, "categoria con id " + newCategory.getId() + " aggiornata con succeso");
        redirectAttributes.addFlashAttribute("message", alertMessage);
        return "redirect:/categories";
    }

    @GetMapping("delete/{id}")
    public String delete (Model model, @PathVariable Integer id, RedirectAttributes redirectAttributes){
        try{
            boolean success = categoryService.deleteCategory(id);
            if (success) {
                AlertMessage alertMessage = new AlertMessage(AlertMessage.AlertMessageType.SUCCESS, "categoria con id " + id + " eliminata con succeso");
                redirectAttributes.addFlashAttribute("message", alertMessage);
            } else {
                AlertMessage alertMessage = new AlertMessage(AlertMessage.AlertMessageType.ERROR, "impossibile eliminare la categoria con id " + id);
                redirectAttributes.addFlashAttribute("message", alertMessage);
            }
        }catch (CategoryNotFoundException e){
            AlertMessage alertMessage = new AlertMessage(AlertMessage.AlertMessageType.ERROR, "categoria con id " + id + " non trovata");
            redirectAttributes.addFlashAttribute("message", alertMessage);
        }
        return "redirect:/categories";
    }
}

