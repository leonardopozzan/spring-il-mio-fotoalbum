package org.learning.springilmiofotoalbum.repository;

import org.learning.springilmiofotoalbum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public boolean existsByNameAndIdNot(String name, Integer id);
    boolean existsByName(String name);

}
