//package edu.eci.arsw.alexandria.config;
//
//import edu.eci.arsw.alexandria.webSocketHandler.EditorWebSocketHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.reactive.HandlerMapping;
//import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
//import org.springframework.web.reactive.socket.WebSocketHandler;
//import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class WebSocketConfig {
//
//    @Bean
//    public HandlerMapping handlerMapping() {
//
//        Map<String, WebSocketHandler> map = new HashMap<>();
//        map.put("/ws", new EditorWebSocketHandler());
//        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//
//        Map<String, CorsConfiguration> corsConfigurationMap = new HashMap<>();
//        mapping.setCorsConfigurations(Collections.singletonMap("*", new CorsConfiguration().applyPermitDefaultValues()));
//        mapping.setUrlMap(map);
////        mapping.setOrder(1); // before annotated controllers
//        return mapping;
//    }
//
//    @Bean
//    public WebSocketHandlerAdapter handlerAdapter() {
//        return new WebSocketHandlerAdapter();
//    }
//}
