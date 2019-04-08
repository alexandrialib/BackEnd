package edu.eci.arsw.alexandria.webSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import edu.eci.arsw.alexandria.service.impl.AlexandriaEditorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.UnicastProcessor;

import java.io.IOException;
import java.util.Optional;



public class EditorWebSocketHandler2 implements WebSocketHandler {

    private UnicastProcessor<Editor> editorPublisher;
    private Flux<String> outputEditors;
    private ObjectMapper mapper;


    public EditorWebSocketHandler2(UnicastProcessor<Editor> editorPublisher, Flux<Editor> editors) {
        this.editorPublisher = editorPublisher;
        this.mapper = new ObjectMapper();
        this.outputEditors = Flux.from(editors).map(this::toJSON);
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        WebSocketMessageSubscriber subscriber = new WebSocketMessageSubscriber(editorPublisher);
        session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .map(this::toEditor)
                .subscribe(subscriber::onNext, subscriber::onError, subscriber::onComplete);
        return session.send(outputEditors.map(session::textMessage));
    }


    private Editor toEditor(String json) {
        try {
            return mapper.readValue(json, Editor.class);
        } catch (IOException e) {
            throw new RuntimeException("Invalid JSON:" + json, e);
        }
    }

    private String toJSON(Editor editor) {
        try {
            return mapper.writeValueAsString(editor);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static class WebSocketMessageSubscriber {
        private UnicastProcessor<Editor> editorPublisher;
        private Optional<Editor> lastReceivedEditor = Optional.empty();

        public WebSocketMessageSubscriber(UnicastProcessor<Editor> editorPublisher) {
            this.editorPublisher = editorPublisher;
        }

        public void onNext(Editor editor) {
            lastReceivedEditor = Optional.of(editor);
            editorPublisher.onNext(editor);
        }

        public void onError(Throwable error) {
            //TODO log error
            error.printStackTrace();
        }

        public void onComplete() {
            lastReceivedEditor.ifPresent(editor -> editorPublisher.onNext(new Editor()));
        }

    }
}