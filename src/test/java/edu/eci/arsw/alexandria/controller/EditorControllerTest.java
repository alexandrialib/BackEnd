package edu.eci.arsw.alexandria.controller;

import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.repositories.EditorRepository;
import edu.eci.arsw.alexandria.service.impl.AlexandriaEditorServiceImpl;
import org.junit.Test;

import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class EditorControllerTest {
    @Test
    public void get(){
        String id = UUID.randomUUID().toString();
        Editor[] editors = {
                new Editor(id,new ArrayList<>(),null),
                new Editor(),
                new Editor(),
                new Editor()
        };
        Flux<Editor> editorFlux = Flux.just(editors);

        EditorRepository repository = Mockito.mock(EditorRepository.class);
        when(repository.findAll()).thenReturn(editorFlux);

        WebTestClient webClient = WebTestClient.bindToController(new EditorController(new AlexandriaEditorServiceImpl(repository)))
                .build();

        webClient.get().uri("/editors/{id}",id).exchange().expectStatus().isOk();

    }
}