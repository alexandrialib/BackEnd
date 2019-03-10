package edu.eci.arsw.alexandria.repositories;

import edu.eci.arsw.alexandria.entities.Article;
import edu.eci.arsw.alexandria.entities.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
    Category getCategoryByName(String name);

}
