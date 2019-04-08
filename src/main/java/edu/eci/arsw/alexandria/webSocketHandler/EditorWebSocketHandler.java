package edu.eci.arsw.alexandria.webSocketHandler;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Component
public class EditorWebSocketHandler implements WebSocketHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    private final AlexandriaEditorService service;

    private List<WebSocketSession> sessions = new ArrayList<>();

    public EditorWebSocketHandler(AlexandriaEditorService service) {
        this.service = service;
    }

    private Flux<WebSocketMessage> sendSomething(WebSocketSession s){
        return s.receive()
                .map(msg -> "the server say: "+msg.getPayloadAsText())
                .map(s::textMessage);
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        if(!sessions.contains(session)) sessions.add(session);
        for(WebSocketSession s: sessions){
            sendSomething(s);
        }
        return session
                .send(
                        sendSomething(session)
                );
    }

    @Bean
    WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

}
