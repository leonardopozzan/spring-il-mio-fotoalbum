package org.learning.springilmiofotoalbum.api;

import jakarta.validation.Valid;
import org.learning.springilmiofotoalbum.model.Image;
import org.learning.springilmiofotoalbum.model.Message;
import org.learning.springilmiofotoalbum.service.CategoryService;
import org.learning.springilmiofotoalbum.service.ImageService;
import org.learning.springilmiofotoalbum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/album")
public class AlbumRestController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/images")
    public List<Image> images(@RequestParam(name= "title")Optional<String> search){
        if(search.isPresent())
            return imageService.getVisibleImagesFiltered(search.get());
        return imageService.getVisibleImages();
    }

    @PostMapping("/message")
    public Map<String, Object > message(@Valid @RequestBody Message message, BindingResult bindingResult){
        System.out.println(message);
        if (bindingResult.hasErrors())
            return Map.of("errors" , bindingResult.getAllErrors());

        messageService.createMessage(message);
        return Map.of("success", true);
    }
}
