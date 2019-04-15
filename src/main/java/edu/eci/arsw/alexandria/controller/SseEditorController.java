package edu.eci.arsw.alexandria.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;


@RestController
@RequestMapping(value = "/sse/editors")
public class SseEditorController {

    @Autowired
    AlexandriaEditorService service;


    @CrossOrigin(origins = "*")
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
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
        getAllEditors();
        return service.create();
    }

    @CrossOrigin(origins = "*")
    @PutMapping()
    private Publisher<Editor> updateEditor(@Valid @RequestBody Editor editor){
        return service.updateEditor(editor);
    }
}
