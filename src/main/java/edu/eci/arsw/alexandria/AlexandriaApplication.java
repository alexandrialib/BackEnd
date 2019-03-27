package edu.eci.arsw.alexandria;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import edu.eci.arsw.alexandria.repositories.CategoryRepository;
import edu.eci.arsw.alexandria.repositories.EditorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class AlexandriaApplication {
    Lorem loremIpsum = LoremIpsum.getInstance();
    public static void main(String[] args) {
        SpringApplication.run(AlexandriaApplication.class, args);
    }


    @Bean
    WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    WebSocketHandler webSocketHandler() {
        return session ->
                session.send(
                        Flux.interval(Duration.ofMillis(1))
                                .map(n -> n.toString())
                                .map(session::textMessage)
                ).and(session.receive()
                        .map(WebSocketMessage::getPayloadAsText)

                );
    }

    @Bean
    HandlerMapping webSocketURLMapping() {
        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        simpleUrlHandlerMapping.setUrlMap(
                Collections.singletonMap("/ws", webSocketHandler()));
        simpleUrlHandlerMapping.setCorsConfigurations(
                Collections.singletonMap("*", new CorsConfiguration().applyPermitDefaultValues()));
        simpleUrlHandlerMapping.setOrder(10);
        return simpleUrlHandlerMapping;
    }

    @Bean
    public CommandLineRunner setupCategory(CategoryRepository categoryRepository) {
        return (args) -> {
            ArrayList<Category> s = new ArrayList<>();
            s.add(new Category("Sort"));
            s.add(new Category("Graph theory"));
            s.add(new Category("Data structures"));
            s.get(0).addArticle((new Article("Quick", loremIpsum.getWords(200))));
            s.get(0).addArticle((new Article("Selection", loremIpsum.getWords(200))));
            s.get(0).addArticle((new Article("Bubble", loremIpsum.getWords(200))));
            s.get(0).addArticle((new Article("Merge", loremIpsum.getWords(200))));
            s.get(1).addArticle((new Article("TopoSort", loremIpsum.getWords(200))));
            s.get(1).addArticle((new Article("MST", loremIpsum.getWords(200))));
            s.get(1).addArticle((new Article("Dijkstra", loremIpsum.getWords(200))));
            categoryRepository.saveAll(s).blockLast();
        };
    }

    @Bean
    public CommandLineRunner setupEditor(EditorRepository editorRepository) {
        return (args) -> {
            ArrayList<Editor> s = new ArrayList<>();
            s.add(new Editor());
            s.get(0).setId("5c992f0922a4ae1086a46fd5");
            editorRepository.saveAll(s).blockLast();
        };
    }
}
