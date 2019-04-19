package edu.eci.arsw.alexandria.controller.SSE;

import edu.eci.arsw.alexandria.model.Editor.Editor;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ReplayProcessor;

import java.time.LocalDateTime;

public class EditorListenerReactive {

    private ReplayProcessor<ServerSentEvent<Editor>> replayProcessor;

    public EditorListenerReactive(){
        this.replayProcessor = ReplayProcessor.create();
    }

    private LocalDateTime last;

    public void onPostEditor(Editor editor, int id) {
        last= LocalDateTime.now();
        editor.setLastEdit(last);
        ServerSentEvent<Editor> event;
            event = ServerSentEvent.builder(editor)
                    .event("editor")
                    .id(id+"").build();
        replayProcessor.onNext(event);
    }

    public Flux<ServerSentEvent<Editor>> subscribe(){
        return replayProcessor.filter(x -> last.equals(x.data().getLastEdit()));
    }
}
