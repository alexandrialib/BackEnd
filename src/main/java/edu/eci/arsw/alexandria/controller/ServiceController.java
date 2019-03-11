package edu.eci.arsw.alexandria.controller;

import edu.eci.arsw.alexandria.entities.Article;
import edu.eci.arsw.alexandria.entities.Category;
import edu.eci.arsw.alexandria.service.AlexandriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class ServiceController {

    @Autowired
    private AlexandriaService service;


    @GetMapping(value = "")
    public ResponseEntity<?> getAllCategories(){
        try {
            return new ResponseEntity<>(service.getAllCategories(), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<?> getCategoryByName(@PathVariable("name") String name){
        try {
            return new ResponseEntity<>(service.getCategoryByName(name), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{name}/articles")
    public ResponseEntity<?> getAllCategoryArticles(@PathVariable("name") String name){
        try {
            return new ResponseEntity<>(service.getCategoryArticles(name), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{name}/articles")
    public ResponseEntity<?> addArticleInCategory(@PathVariable("name") String name,@RequestBody Article article){
        try {
            service.saveArticleInCategory(name,article);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveCategory( @RequestBody Category category){
        try {
            service.saveCategory(category);
            return new ResponseEntity<>( HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
