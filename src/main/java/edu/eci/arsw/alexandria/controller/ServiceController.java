package edu.eci.arsw.alexandria.controller;

import edu.eci.arsw.alexandria.entities.Article;
import edu.eci.arsw.alexandria.entities.Category;
import edu.eci.arsw.alexandria.service.AlexandriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {

    @Autowired
    private AlexandriaService service;

    @GetMapping(value = "/articles")
    public ResponseEntity<?> getAllArticles(){
        try {
            return new ResponseEntity<>(service.getAllArticles(), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/categories")
    public ResponseEntity<?> getAllCategories(){
        try {
            service.getAllCategories();
            return new ResponseEntity<>(service.getAllCategories(), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/categories/{name}")
    public ResponseEntity<?> getCategory(@PathVariable("name") String name){
        try {
            return new ResponseEntity<>(service.getCategoryByName(name), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/articles/{name}")
    public ResponseEntity<?> getArticle(@PathVariable("name") String name){
        try {
            return new ResponseEntity<>(service.getArticleByName(name), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/categories")
    public ResponseEntity<?> saveCategory( @RequestBody Category category){
        try {
            service.saveCategory(category);
            return new ResponseEntity<>( HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/categories/{name}")
    public ResponseEntity<?> saveArticleInCategory(@PathVariable("name")String name, @RequestBody Article article){
        try {
            service.getCategoryByName(name).getArticles().add(article);
            return new ResponseEntity<>( HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
