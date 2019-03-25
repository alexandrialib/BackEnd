package edu.eci.arsw.alexandria.controller;

import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;


@RestController
@CrossOrigin
@RequestMapping(value = "/editors")
public class EditorController {

    @Autowired
    AlexandriaEditorService service;

    @GetMapping()
    private Publisher<Editor> getAllEditors(){
        return service.getEditors();
    }

    @GetMapping(value = "/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Mono<Editor> getEditorById(@PathVariable("id")String id){
        return service.getEditorById(id);
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

