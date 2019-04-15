package edu.eci.arsw.alexandria.controller;

import edu.eci.arsw.alexandria.model.KnowledgeBase.User;
import edu.eci.arsw.alexandria.repositories.UserRepository;
import edu.eci.arsw.alexandria.service.AlexandriaUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@PreAuthorize("hasRole('USER')")
public class UserController {

    private final AlexandriaUserService service;

    UserController( AlexandriaUserService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public Mono<User> current(@AuthenticationPrincipal Mono<User> principal) {
        return principal;
    }

    @PostMapping("/users")
    @PreAuthorize("true")
    public Mono<?> addUser(@RequestBody User user){
        try {
            return  service.addUser(user);
        } catch (Exception e) {
            return Mono.empty();
        }
    }
}
