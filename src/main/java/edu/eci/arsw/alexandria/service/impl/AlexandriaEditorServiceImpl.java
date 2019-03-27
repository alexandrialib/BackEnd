package edu.eci.arsw.alexandria.service.impl;

import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.repositories.EditorRepository;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AlexandriaEditorServiceImpl implements AlexandriaEditorService {

    private final EditorRepository repository;
    private final ApplicationEventPublisher publisher;

    public AlexandriaEditorServiceImpl(EditorRepository repository, ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
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
        return repository.findById(id).flatMap(x -> {
            x.setText(text);
            return Mono.just(x);
        })
//                .flatMap(this.repository::save).doOnSuccess(entity -> this.publisher.publishEvent(new EditorUpdateEvent(entity)))
                .flatMapIterable(x -> x.getText());
    }
}
