package edu.eci.arsw.alexandria.repositories;

import edu.eci.arsw.alexandria.model.KnowledgeBase.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User,String> {
    Mono<User> findByUsername(String userName);
}
