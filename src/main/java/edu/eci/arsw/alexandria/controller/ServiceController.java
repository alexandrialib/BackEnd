package edu.eci.arsw.alexandria.controller;

import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import edu.eci.arsw.alexandria.service.AlexandriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/categories", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public class ServiceController {

    @Autowired
    private AlexandriaService service;


    @GetMapping(value = "")
    public ResponseEntity<Flux<Category>> getAllCategories(){
        try {
            return new ResponseEntity<>(service.getAllCategories(),HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(service.getAllCategories(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<Mono<Category>> getCategoryByName(@PathVariable("name") String name){
        try {
            return new ResponseEntity<>(service.getCategoryByName(name), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{name}/articles")
    public ResponseEntity<Flux<Article>> getAllCategoryArticles(@PathVariable("name") String name){
        try {
            return new ResponseEntity<>(service.getCategoryArticles(name), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{name}/articles")
    public ResponseEntity<Mono<Article>> addArticleInCategory(@PathVariable("name") String name,@RequestBody Article article){
        try {
            service.saveArticleInCategory(name,article);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Flux<Category>> saveCategory( @RequestBody Category category){
        try {
            service.saveCategory(category);
            return new ResponseEntity<>( HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
