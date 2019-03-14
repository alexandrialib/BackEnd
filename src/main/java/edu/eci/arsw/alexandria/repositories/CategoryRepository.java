package edu.eci.arsw.alexandria.repositories;

import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;


public interface CategoryRepository extends ReactiveMongoRepository<Category, ObjectId> {
    Mono<Category> getCategoryByName(String name);
}
