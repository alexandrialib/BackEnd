package edu.eci.arsw.alexandria.controller;

import edu.eci.arsw.alexandria.model.KnowledgeBase.User;
import edu.eci.arsw.alexandria.repositories.UserRepository;
import edu.eci.arsw.alexandria.service.AlexandriaUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    private final AlexandriaUserService service;

    UserController( AlexandriaUserService service) {
        this.service = service;
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public Mono<User> current(@AuthenticationPrincipal Mono<User> principal) {
        return principal;
    }

    @CrossOrigin("*")
    @PostMapping("/users")
    public Mono<?> addUser(@RequestBody User user){
        try {
            System.out.println(user);
            return  service.addUser(user);
        } catch (Exception e) {
            return Mono.empty();
        }
    }
}
