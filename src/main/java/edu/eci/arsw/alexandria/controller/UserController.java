package edu.eci.arsw.alexandria.controller;

import edu.eci.arsw.alexandria.model.KnowledgeBase.User;
import edu.eci.arsw.alexandria.repositories.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    private final UserRepository users;

    UserController(UserRepository users) {
        this.users = users;
    }

    @GetMapping("/user")
    public Mono<User> current(@AuthenticationPrincipal Mono<User> principal) {
        return principal;
    }

    @GetMapping("/users/{username}")
    public Mono<User> get(@PathVariable() String username) {
        return this.users.findByUsername(username);
    }
}
