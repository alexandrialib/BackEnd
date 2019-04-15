package edu.eci.arsw.alexandria.service;

import edu.eci.arsw.alexandria.model.KnowledgeBase.User;
import reactor.core.publisher.Mono;

public interface AlexandriaUserService {
    Mono<User> getUserByUserName(String username);
    Mono<User> addUser(User user) throws Exception;
    Mono<User> deleteUserByUserName(String username);
    Mono<User> activeUserByUserName(String username);
}
