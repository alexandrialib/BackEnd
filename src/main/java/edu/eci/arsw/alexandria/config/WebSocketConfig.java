package edu.eci.arsw.alexandria.config;

import edu.eci.arsw.alexandria.webSocketHandler.EditorWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebSocketConfig {

    @Bean
    HandlerMapping webSocketURLMapping(WebSocketHandler handler) {
        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        simpleUrlHandlerMapping.setUrlMap(
                Collections.singletonMap("/ws/editors", handler));
        simpleUrlHandlerMapping.setCorsConfigurations(
                Collections.singletonMap("*", new CorsConfiguration().applyPermitDefaultValues()));
        simpleUrlHandlerMapping.setOrder(10);
        return simpleUrlHandlerMapping;
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}
