package edu.eci.arsw.alexandria.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/sse/editors")
public class SseEditorController {

    @Autowired
    AlexandriaEditorService service;


    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin(origins = "*")
    private Flux<Editor> getAllEditors(){
        return service.getEditors();
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
