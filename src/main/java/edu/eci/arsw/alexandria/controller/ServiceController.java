package edu.eci.arsw.alexandria.controller;

import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import edu.eci.arsw.alexandria.service.AlexandriaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/categories")
public class ServiceController {

    @Autowired
    private AlexandriaCategoryService service;


    @GetMapping()
    public Flux<ResponseEntity<Category>> getAllCategories(){
        return service.getAllCategories().map(x -> ResponseEntity.ok(x)).defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "{name}")
    public Mono<ResponseEntity<Category>> getCategoryByName(@PathVariable("name") String name){
        return service.getCategoryByName(name).map(x-> ResponseEntity.ok(x)).defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @GetMapping(value = "{name}/articles")
    public Flux<ResponseEntity<Article>> getAllCategoryArticles(@PathVariable("name") String name){
        return service.getCategoryArticles(name).map(x -> ResponseEntity.ok(x)).defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @PostMapping(value = "{name}/articles")
    public Flux<Article> addArticleInCategory(@PathVariable("name") String name,@RequestBody Article article){
        return service.saveArticleInCategory(name,article);
    }

    @PostMapping()
    public Mono<Category> saveCategory( @RequestBody Category category){
        return service.saveCategory(category);
    }

}
