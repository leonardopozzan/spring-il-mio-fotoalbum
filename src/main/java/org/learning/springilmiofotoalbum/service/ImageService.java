package org.learning.springilmiofotoalbum.service;

import org.learning.springilmiofotoalbum.model.Image;
import org.learning.springilmiofotoalbum.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public List<Image> getAllImages(){
        return imageRepository.findAll();
    }

    public List<Image> getFilteredImages(String keyword) {
        return imageRepository.findByTitleContainingIgnoreCase(keyword);
    }
}
