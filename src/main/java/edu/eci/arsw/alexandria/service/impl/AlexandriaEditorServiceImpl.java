package edu.eci.arsw.alexandria.service.impl;

import edu.eci.arsw.alexandria.controller.SSE.Editor.EditorListenerReactive;
import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.repositories.EditorRepository;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AlexandriaEditorServiceImpl implements AlexandriaEditorService {

    private final EditorRepository repository;

    private Map<String, EditorListenerReactive> reactiveMap = new ConcurrentHashMap<>();

    public AlexandriaEditorServiceImpl(EditorRepository repository) {
        this.repository = repository;
    }


    @Override
    public Flux<Editor> getEditors() {
        return repository.findAll();
    }


    @Override
    public Mono<Editor> getEditorById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Editor> updateEditor(Editor editor) {

//        repository.findById(editor.getId()).subscribe(listenerReactive::onPostEditor);
        return repository.save(editor);
    }

    @Override
    public Mono<Void> deleteEditor(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Editor> addEditor(Editor editor) {
        return repository.insert(editor);
    }

    @Override
    public Mono<Editor> create(){
        return this.repository
                .save(new Editor());
    }

    @Override
    public Flux<String> updateString(String id, List<String> text) {
        return
        repository.findById(id).flatMap(x -> {
            x.setText(text);
            return Mono.just(x);
        }).flatMap(this.repository::save)
                .flatMapIterable(x -> x.getText());
    }
}
