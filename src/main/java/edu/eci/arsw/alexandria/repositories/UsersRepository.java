package edu.eci.arsw.alexandria.repositories;

import edu.eci.arsw.alexandria.model.Security.Users;
import reactor.core.publisher.Mono;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends ReactiveMongoRepository<Users, String> {
    Mono<Users> findByUsername(String name);
}
