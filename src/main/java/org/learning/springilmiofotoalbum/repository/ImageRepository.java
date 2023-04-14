package org.learning.springilmiofotoalbum.repository;

import org.learning.springilmiofotoalbum.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByTitleContainingIgnoreCase(String title);
    boolean existsByTitleAndIdNot(String name, Integer id);

    boolean existsByTitle(String title);
}
