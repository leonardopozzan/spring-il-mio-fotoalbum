package org.learning.springilmiofotoalbum.repository;

import org.learning.springilmiofotoalbum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByNameAndIdNot(String name, Integer id);
    boolean existsByName(String name);

    List<Category> findByIdNotIn(List<Integer> ids);
}
