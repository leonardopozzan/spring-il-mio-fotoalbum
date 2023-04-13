package org.learning.springilmiofotoalbum.repository;

import org.learning.springilmiofotoalbum.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    public List<Image> findByTitleContainingIgnoreCase(String title);
}
