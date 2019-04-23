package edu.eci.arsw.alexandria.controller.SSE.Paint;


import edu.eci.arsw.alexandria.model.Paint.Figure;
import edu.eci.arsw.alexandria.model.Paint.Paint;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(value = "/sse/paints")
public class SsePaintController {

    AtomicInteger ids = new AtomicInteger(0);
    Map<String, PaintListenerReactive> map = new ConcurrentHashMap<>();

    @GetMapping
    public void getPaints(){

    }

    @GetMapping("{id}")
    public Flux<ServerSentEvent<Paint>> getPaints(@PathVariable String id){
        if(map.get(id)==null){
            System.out.println("in!");
            map.put(id, new PaintListenerReactive());
        }
        return map.get(id).subscribe();
    }

    @PutMapping(value = "{id}")
    public void updatePaint(@PathVariable String id,@RequestBody Paint paint){
        if(map.get(id)==null){
            map.put(id,new PaintListenerReactive());
        }
        map.get(id).onPostPaint(paint, ids.getAndIncrement());
    }
}
