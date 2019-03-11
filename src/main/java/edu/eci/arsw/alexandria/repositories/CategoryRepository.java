package edu.eci.arsw.alexandria.repositories;

import edu.eci.arsw.alexandria.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryByName(String name);

}
