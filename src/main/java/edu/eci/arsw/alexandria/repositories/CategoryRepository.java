package edu.eci.arsw.alexandria.repositories;

import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
    Category getCategoryByName(String name);

}
