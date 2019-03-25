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
    @CrossOrigin(origins = "*")
    public Flux<Category> getAllCategories(){
        return service.getAllCategories();
    }

    @GetMapping(value = "{name}")
    @CrossOrigin(origins = "*")
    public Mono<Category> getCategoryByName(@PathVariable("name") String name){
        return service.getCategoryByName(name);

    }

    @GetMapping(value = "{name}/articles")
    @CrossOrigin(origins = "*")
    public Flux<Article> getAllCategoryArticles(@PathVariable("name") String name){
        return service.getCategoryArticles(name);
    }

    @GetMapping(value = "{name}/articles/{articleName}")
    @CrossOrigin(origins = "*")
    public Mono<Article> getAllCategoryArticles(@PathVariable("name") String name,@PathVariable("articleName") String articleName){
        return service.getCategoryArticleByName(name,articleName);
    }

    @PostMapping(value = "{name}/articles")
    @CrossOrigin(origins = "*")
    public Flux<Article> addArticleInCategory(@PathVariable("name") String name,@RequestBody Article article){
        return service.saveArticleInCategory(name,article);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public Mono<Category> saveCategory( @RequestBody Category category){
        return service.saveCategory(category);
    }

}
