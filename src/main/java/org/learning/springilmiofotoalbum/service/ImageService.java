package org.learning.springilmiofotoalbum.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.learning.springilmiofotoalbum.exception.CategoryNotFoundException;
import org.learning.springilmiofotoalbum.exception.ImageNotFoundException;
import org.learning.springilmiofotoalbum.model.Category;
import org.learning.springilmiofotoalbum.model.Image;
import org.learning.springilmiofotoalbum.repository.CategoryRepository;
import org.learning.springilmiofotoalbum.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Image> getAllImages(){
        return imageRepository.findAll();
    }

    public List<Image> getFilteredImages(String keyword) {
        return imageRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public Image getById(Integer id){
        return imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }

    public Image createImage(Image formImage) {
        Image newImage = new Image(formImage);
        newImage.setCategories(getCategoriesFromImage(formImage));
        return imageRepository.save(newImage);
    }

    public List<Category> getCategoriesFromImage(Image image){
        Set<Category> categoriesFromImage = new HashSet<>();
        for (Category category : image.getCategories()){
            categoriesFromImage.add(categoryRepository.findById(category.getId()).orElseThrow(CategoryNotFoundException::new));
        }
        return new ArrayList<>(categoriesFromImage);
    }
}
