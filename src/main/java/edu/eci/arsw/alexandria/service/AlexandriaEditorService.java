package edu.eci.arsw.alexandria.service;

import edu.eci.arsw.alexandria.model.Editor.Editor;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AlexandriaEditorService {
    Flux<Editor> getEditors();
    Mono<Editor> getEditorById(String id);
    Mono<Editor> updateEditor(Editor editor);
    Mono<Void> deleteEditor(String id);
    Mono<Editor> addEditor(Editor editor);
    Mono<Editor> create();
    Flux<String> updateString(String id, List<String> text);
}
