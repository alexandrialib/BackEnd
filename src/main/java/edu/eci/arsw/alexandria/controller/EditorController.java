package edu.eci.arsw.alexandria.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@RestController
@RequestMapping(value = "/editors")
public class EditorController {

    @Autowired
    AlexandriaEditorService service;

    @GetMapping()
    @CrossOrigin(origins = "*")
    private Publisher<Editor> getAllEditors(){
        return service.getEditors();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Publisher<Editor> getEditorById(@PathVariable("id")String id){
        return service.getEditorById(id);
    }

    @GetMapping(value = "/{id}/text",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin(origins = "*")
    private Publisher<String> getEditorText(@PathVariable("id")String id){
        return service.getEditorById(id).flatMapIterable(x -> x.getText());
    }

    @PutMapping(value = "/{id}/text",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin(origins = "*")
    private Publisher<String> getEditorText(@PathVariable("id")String id, @RequestBody List<String> texts){
        return service.updateString(id,texts);
    }

    @PostMapping("create")
    @CrossOrigin(origins = "*")
    private Publisher<Editor> createEditorByUser(){
        return service.create();
    }

    @CrossOrigin(origins = "*")
    @PutMapping()
    private Publisher<Editor> updateEditor(@Valid @RequestBody Editor editor){
        return service.updateEditor(editor);
    }
}
