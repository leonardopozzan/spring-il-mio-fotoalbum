package org.learning.springilmiofotoalbum.service;

import org.learning.springilmiofotoalbum.exception.CategoryNotFoundException;
import org.learning.springilmiofotoalbum.model.Category;
import org.learning.springilmiofotoalbum.model.Image;
import org.learning.springilmiofotoalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public boolean isValidName(Category formCategory){
        if(formCategory.getId() == null){
            return !categoryRepository.existsByName(formCategory.getName());
        }
        return !categoryRepository.existsByNameAndIdNot(formCategory.getName(), formCategory.getId());
    }

    public Category createCategory(Category formCategory) {
        Category newCategory = new Category();
        newCategory.setName(formCategory.getName());
        return categoryRepository.save(newCategory);
    }

    public Category getById(Integer id) throws CategoryNotFoundException{
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    public Category updateCategory(Category formCategory, Integer id) throws CategoryNotFoundException {
        Category categoryToUpdate = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        categoryToUpdate.setName(formCategory.getName());
        return categoryRepository.save(categoryToUpdate);
    }

    public boolean deleteCategory(Integer id) throws CategoryNotFoundException{
        categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        try{
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public List<Category> getMissingCategories(Image image) {
        List<Integer> categoriesIds = image.getCategories().stream().map((element) -> element.getId()).toList();
        return categoryRepository.findByIdNotIn(categoriesIds);
    }
}
