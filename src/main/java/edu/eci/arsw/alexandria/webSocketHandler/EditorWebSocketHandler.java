package edu.eci.arsw.alexandria.webSocketHandler;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arsw.alexandria.model.Editor.Editor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static java.time.LocalDate.now;
import static java.util.UUID.randomUUID;


@Service
public class EditorWebSocketHandler implements WebSocketHandler {

//    private final ObjectMapper mapper = new ObjectMapper();
//    private Flux<Editor> flux;
//    private FluxSink<Editor> webSocketEditorSink;
//
//    public EditorWebSocketHandler() {
//        this.flux = Flux.<Editor>create(
//                emitter -> this.webSocketEditorSink = emitter,
//                FluxSink.OverflowStrategy.IGNORE)
//                .publish()
//                .autoConnect();
//    }
//
//
//    @Override
//    public Mono<Void> handle(WebSocketSession webSocketSession) {
//        return webSocketSession.send(
//                flux.map(editor -> {
//                    try {
//                        return mapper.writeValueAsString(editor);
//                    } catch (JsonProcessingException e) {
//                        throw new RuntimeException(e);
//                    }
//                })
//                        .map(webSocketSession::textMessage))
//                .and(webSocketSession.receive()
//                        .map(WebSocketMessage::getPayloadAsText).log());
//    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(
                Flux.interval(Duration.ofMillis(1))
                        .map(n -> n.toString())
                        .map(session::textMessage)
        ).and(session.receive()
                .map(WebSocketMessage::getPayloadAsText)
        );
    }
}
