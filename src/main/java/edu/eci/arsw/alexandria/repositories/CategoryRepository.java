package edu.eci.arsw.alexandria.repositories;

import edu.eci.arsw.alexandria.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RepositoryRestResource(path="categories", collectionResourceRel = "categories")
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @GetMapping("{name}")
    Category getCategoryByName(@PathVariable("name") String name);
}
