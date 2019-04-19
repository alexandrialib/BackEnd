package edu.eci.arsw.alexandria.controller;

import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/editors")
public class EditorController {

    private final AlexandriaEditorService service;

    public EditorController(AlexandriaEditorService service) {
        this.service = service;
    }

    @GetMapping()
    private Publisher<Editor> getAllEditors(){
        return service.getEditors();
    }

    @GetMapping(value = "/{id}")
    private Publisher<Editor> getEditorById(@PathVariable("id")String id){
        return service.getEditorById(id);
    }

    @GetMapping(value = "/{id}/text")
    private Publisher<String> getEditorText(@PathVariable("id")String id){
        return service.getEditorById(id).flatMapIterable(x -> x.getText());
    }

    @PutMapping(value = "/{id}/text")
    private Publisher<String> getEditorText(@PathVariable("id")String id, @RequestBody List<String> texts){
        return service.updateString(id,texts);
    }

    @PostMapping("create")
    private Publisher<Editor> createEditorByUser(){
        return service.create();
    }

    @PutMapping()
    private Publisher<Editor> updateEditor(@Valid @RequestBody Editor editor){
        return service.updateEditor(editor);
    }
}
