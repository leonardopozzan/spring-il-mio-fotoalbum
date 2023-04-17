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

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

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

    public Image getById(Integer id) throws ImageNotFoundException{
        return imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }

    public Image createImage(Image formImage) throws CategoryNotFoundException {
        Image newImage = new Image(formImage);
        newImage.setCategories(getCategoriesFromImage(formImage));
        return imageRepository.save(newImage);
    }

    private List<Category> getCategoriesFromImage(Image image) throws CategoryNotFoundException{
        Set<Category> categoriesFromImage = new HashSet<>();
        for (Category category : image.getCategories()){
            categoriesFromImage.add(categoryRepository.findById(category.getId()).orElseThrow( ()-> new CategoryNotFoundException(category.getName())));
        }
        return new ArrayList<>(categoriesFromImage);
    }

    public boolean isValidTitle(Image formImage){
        if(formImage.getId() == null){
            return !imageRepository.existsByTitle(formImage.getTitle());
        }
        return !imageRepository.existsByTitleAndIdNot(formImage.getTitle(), formImage.getId());
    }

    public boolean isValidURL(String url)  {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

    public Image updateImage(Image formImage, Integer id) throws ImageNotFoundException, CategoryNotFoundException {
        Image imageToUpdate = imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
        imageToUpdate.copyFrom(formImage);
        imageToUpdate.setCategories(getCategoriesFromImage(formImage));
        return imageRepository.save(imageToUpdate);
    }

    public boolean deleteImage(Integer id) throws ImageNotFoundException{
        imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
        try{
            imageRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public List<Image> getVisibleImages() {
        return imageRepository.findByVisibleTrue();
    }

    public List<Image> getVisibleImagesFiltered(String s) {
        return imageRepository.findByVisibleTrueAndTitleContainingIgnoreCase(s);
    }

    public Image addCategoryToImage(Integer imageId, Integer categoryId) {
        Image imageToUpdate = imageRepository.findById(imageId).orElseThrow(ImageNotFoundException::new);
        Category categoryToAdd = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        Set<Category> setOfCategories = new HashSet<>(imageToUpdate.getCategories());
        setOfCategories.add(categoryToAdd);
        List<Category> listOfCategories = new ArrayList<>(setOfCategories);
        imageToUpdate.setCategories(listOfCategories);
        return imageRepository.save(imageToUpdate);
    }

    public Image removeCategoryFromImage(Integer imageId, Integer categoryId) {
        Image imageToUpdate = imageRepository.findById(imageId).orElseThrow(ImageNotFoundException::new);
        Category categoryToRemove = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        imageToUpdate.getCategories().remove(categoryToRemove);
        return imageRepository.save(imageToUpdate);
    }
}
