package edu.eci.arsw.alexandria.repositories;

import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
    Mono<Category> getCategoryByName(String name);
}
