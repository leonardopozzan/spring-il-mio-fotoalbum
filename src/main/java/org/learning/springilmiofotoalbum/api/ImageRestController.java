package org.learning.springilmiofotoalbum.api;

import org.learning.springilmiofotoalbum.model.Image;
import org.learning.springilmiofotoalbum.service.CategoryService;
import org.learning.springilmiofotoalbum.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/album")
public class ImageRestController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/images")
    public List<Image> images(@RequestParam(name= "title")Optional<String> search){
        if(search.isPresent())
            return imageService.getVisibleImagesFiltered(search.get());
        return imageService.getVisibleImages();
    }


}
