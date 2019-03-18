package edu.eci.arsw.alexandria.service;

import edu.eci.arsw.alexandria.model.Editor.Editor;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlexandriaEditorService {
    Flux<Editor> getEditors();
    Mono<Editor> getEditorById(ObjectId id);
    void updateEditor(Editor editor);
    void deleteEditor(ObjectId id);
    void addEditor(Editor editor);
}
