package edu.eci.arsw.alexandria.service.impl;

import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.repositories.EditorRepository;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import org.bson.types.ObjectId;
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
    public Mono<Editor> getEditorById(ObjectId id) {
        return editorRepository.findById(id);
    }

    @Override
    public void updateEditor(Editor editor) {
        editorRepository.save(editor);
    }

    @Override
    public void deleteEditor(ObjectId id) {
        editorRepository.deleteById(id);
    }

    @Override
    public void addEditor(Editor editor) {
        editorRepository.insert(editor);
    }
}
