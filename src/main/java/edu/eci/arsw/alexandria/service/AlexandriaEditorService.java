package edu.eci.arsw.alexandria.service;

import edu.eci.arsw.alexandria.model.Editor.Editor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlexandriaEditorService {
    Flux<Editor> getEditors();
    Mono<Editor> getEditorById(String id);
    Mono<Editor> updateEditor(Editor editor);
    void deleteEditor(String id);
    Mono<Editor> addEditor(Editor editor);
}
