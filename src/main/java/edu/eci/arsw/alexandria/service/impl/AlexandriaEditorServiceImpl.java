package edu.eci.arsw.alexandria.service.impl;

import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.repositories.EditorRepository;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlexandriaEditorServiceImpl implements AlexandriaEditorService {

    @Autowired
    EditorRepository editorRepository;

    @Override
    public Flux<Editor> getEditors() {
        return editorRepository.findAll();
    }

    @Override
    public Mono<Editor> getEditorById(String id) {
        return editorRepository.findById(id);
    }

    @Override
    public Mono<Editor> updateEditor(Editor editor) {
        return editorRepository.save(editor);
    }

    @Override
    public void deleteEditor(String id) {
        editorRepository.deleteById(id);
    }

    @Override
    public Mono<Editor> addEditor(Editor editor) {
        return editorRepository.insert(editor);
    }
}
