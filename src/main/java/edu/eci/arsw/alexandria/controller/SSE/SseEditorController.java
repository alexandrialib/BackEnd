package edu.eci.arsw.alexandria.controller.SSE;

import edu.eci.arsw.alexandria.controller.SSE.EditorListenerReactive;
import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.service.AlexandriaEditorService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping(value = "/sse/editors")
public class SseEditorController {

    @Autowired
    AlexandriaEditorService service;

    AtomicInteger ids = new AtomicInteger(0);
    Map<String, EditorListenerReactive> map = new ConcurrentHashMap<>();


    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<Editor> getAllEditors(){
        return service.getEditors();
    }

    @GetMapping(value = "/{id}/text",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Publisher<String> getEditorText(@PathVariable("id")String id){
        return service.getEditorById(id).flatMapIterable(x -> x.getText());
    }

    @PutMapping(value = "/{id}/text",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Publisher<String> getEditorText(@PathVariable("id")String id, @RequestBody List<String> texts){
        return service.updateString(id,texts);
    }

    @PostMapping("create")
    private Publisher<Editor> createEditorByUser(){
        getAllEditors();
        return service.create();
    }

    @PutMapping()
    private Publisher<Editor> updateEditor(@Valid @RequestBody Editor editor){
        return service.updateEditor(editor);
    }

    @PostMapping(value = "/v2/{id}")
    public void PostMessage(@PathVariable("id") String id,@RequestBody Editor editor){
        if(map.get(id)==null){
            map.put(id, new EditorListenerReactive());
        }
        map.get(id).onPostEditor(editor, ids.getAndIncrement());
    }


    @GetMapping(value = "/v2/{id}",produces = "text/event-stream")
    public Flux<ServerSentEvent<Editor>> subscribe(@PathVariable("id") String id){
        if(map.get(id)==null){
            map.put(id, new EditorListenerReactive());
        }
        return map.get(id).subscribe();
    }
}
