package edu.eci.arsw.alexandria.controller;

import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(value = "/editors")
public class EditorController {

    @Autowired
    AlexandriaEditorService service;

    @GetMapping()
    private ResponseEntity<Flux<Editor>> getAllEditors(){
        return new ResponseEntity<Flux<Editor>>(service.getEditors(),HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<Mono<Editor>> getEditorById(@PathVariable("id")ObjectId id){
        return new ResponseEntity<>(service.getEditorById(id), HttpStatus.ACCEPTED);
    }

    @PostMapping()
    private ResponseEntity<?> createEditorByUser(@RequestBody Editor editor){
        service.addEditor(editor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    private ResponseEntity<?> updateEditor(@RequestBody Editor editor){
        service.updateEditor(editor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
