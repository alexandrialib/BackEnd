package edu.eci.arsw.alexandria.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
@ComponentScan
public class WebFluxConfig {
}
