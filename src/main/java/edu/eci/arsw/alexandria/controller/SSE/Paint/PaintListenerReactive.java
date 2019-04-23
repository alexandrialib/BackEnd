package edu.eci.arsw.alexandria.controller.SSE.Paint;

import edu.eci.arsw.alexandria.model.Paint.Paint;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ReplayProcessor;

import java.time.LocalDateTime;

public class PaintListenerReactive {

    private ReplayProcessor<ServerSentEvent<Paint>> replayProcessor;

    public PaintListenerReactive(){
        this.replayProcessor = ReplayProcessor.create();
    }

    private LocalDateTime last;

    public void onPostPaint(Paint paint, int id) {
        last= LocalDateTime.now();
        paint.setLastModify(last);
        ServerSentEvent<Paint> event;
            event = ServerSentEvent.builder(paint)
                    .event("paint")
                    .id(id+"").build();
        replayProcessor.onNext(event);
    }

    public Flux<ServerSentEvent<Paint>> subscribe(){
        return replayProcessor.filter(x -> last.equals(x.data().getLastModify()));
    }
}
