package edu.eci.arsw.alexandria.controller;

import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/editors")
public class EditorController {

    @Autowired
    AlexandriaEditorService service;

    @GetMapping()
    private Flux<ResponseEntity<Editor>> getAllEditors(){
        return service.getEditors().map(x -> ResponseEntity.ok(x)).defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/{id}")
    private Mono<ResponseEntity<Editor>> getEditorById(@PathVariable("id")String id){
        return service.getEditorById(id)
                .map( x -> ResponseEntity.ok(x))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping()
    private Mono<Editor> createEditorByUser(@Valid @RequestBody Editor editor){
        return service.addEditor(editor);
    }

    @PutMapping()
    private Mono<Editor> updateEditor(@Valid @RequestBody Editor editor){
        return service.updateEditor(editor);
    }
}
