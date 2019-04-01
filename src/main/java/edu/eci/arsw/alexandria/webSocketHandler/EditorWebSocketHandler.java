package edu.eci.arsw.alexandria.webSocketHandler;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.repositories.EditorRepository;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.time.LocalDate.now;
import static java.util.UUID.randomUUID;


@Component
public class EditorWebSocketHandler implements WebSocketHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    private final AlexandriaEditorService service;

    private List<WebSocketSession> sessions = new ArrayList<>();

    public EditorWebSocketHandler(AlexandriaEditorService service) {
        this.service = service;
    }

    private Flux<String> eventFlux = Flux.generate(sink -> {
        Editor event = new Editor();
        event.setId(UUID.randomUUID().toString());
        try {
            sink.next(mapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            sink.error(e);
        }
    });

    private Flux<String> intervalFlux = Flux.interval(Duration.ofMillis(1000L))
            .zipWith(eventFlux, (time, event) -> event);


    private void sendSomething(){
        System.out.println("entro");
        for(WebSocketSession s:sessions){
            s.textMessage("");
        }
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        if(!sessions.contains(session)) sessions.add(session);
//        System.out.println(sessions);
//        sendSomething();
//        return session.send(
//                service.getEditors()
//                        .map(x -> {
//                            try {
//                                return mapper.writeValueAsString(x);
//                            } catch (JsonProcessingException e) {
//                                throw  new RuntimeException(e);
//                            }
//                        }).map(session::textMessage)
//        ).and(session.receive()
//                .map(WebSocketMessage::getPayloadAsText)
//                .doOnNext(msg -> sendSomething())
//        );
//        return session.send(intervalFlux
//                .map(session::textMessage))
//                .and(session.receive()
//                        .map(WebSocketMessage::getPayloadAsText)
//                        .doOnNext(x -> System.out.println(x)));
        return session
                .send( session.receive()

                        .map(msg -> "RECEIVED ON SERVER :: " + msg.getPayloadAsText())
                        .map(session::textMessage)
                );
    }

    @Bean
    WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

}
