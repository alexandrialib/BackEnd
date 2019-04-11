package edu.eci.arsw.alexandria.service.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import edu.eci.arsw.alexandria.repositories.UserRepository;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MongoUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Mono<edu.eci.arsw.alexandria.model.KnowledgeBase.User> userM = repository.findByUserName(username);
    edu.eci.arsw.alexandria.model.KnowledgeBase.User user = userM.block();
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    List<SimpleGrantedAuthority> authorities = new ArrayList();
    for(String role: user.getRoles()) {
      authorities.add(new SimpleGrantedAuthority(role));
    }
    return new User(user.getUserName(), user.getPassword(), authorities);
  }
}