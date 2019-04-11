package edu.eci.arsw.alexandria.repositories;


import edu.eci.arsw.alexandria.model.KnowledgeBase.User;
import reactor.core.publisher.Mono;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUserName(String name);
}
