package edu.eci.arsw.alexandria.service.impl;

import edu.eci.arsw.alexandria.model.KnowledgeBase.User;
import edu.eci.arsw.alexandria.repositories.UserRepository;
import edu.eci.arsw.alexandria.service.AlexandriaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AlexandriaUserServiceImpl implements AlexandriaUserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;


    @Override
    public Mono<User> getUserByUserName(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Mono<User> addUser(User user) throws Exception {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.findByUsername(
                user.getUsername())
                .switchIfEmpty(this.repository.save(user)
        );
    }

    @Override
    public Mono<User> deleteUserByUserName(String username) {
        return null;
    }

    @Override
    public Mono<User> activeUserByUserName(String username) {
        return null;
    }
}
