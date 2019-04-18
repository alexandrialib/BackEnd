package edu.eci.arsw.alexandria.controller;

import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Comment;
import edu.eci.arsw.alexandria.service.AlexandriaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.reactivestreams.Publisher;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private AlexandriaCategoryService service;


    @GetMapping()
    @CrossOrigin(origins = "*")
    public Publisher<Category> getAllCategories(){
        return service.getAllCategories();
    }

    @GetMapping(value = "{name}")
    @CrossOrigin(origins = "*")
    public Publisher<Category> getCategoryByName(@PathVariable("name") String name){
        return service.getCategoryByName(name);

    }

    @GetMapping(value = "{name}/articles")
    @CrossOrigin(origins = "*")
    public Publisher<Article> getAllCategoryArticles(@PathVariable("name") String name){
        return service.getCategoryArticles(name);
    }

    @GetMapping(value = "{name}/articles/{articleName}")
    @CrossOrigin(origins = "*")
    public Publisher<Article> getAllCategoryArticles(@PathVariable("name") String name,@PathVariable("articleName") String articleName){
        return service.getCategoryArticleByName(name,articleName);
    }

    @GetMapping(value = "{name}/articles/{articleName}/comments")
    @CrossOrigin(origins = "*")
    public Publisher<Comment> getAllCategoryArticlesComments(@PathVariable("name") String name, @PathVariable("articleName") String articleName){
        return service.getCategoryArticleByNameComments(name,articleName);
    }

    @PostMapping(value = "{name}/articles/{articleName}/comments")
    @PreAuthorize("hasRole('USER')")
    @CrossOrigin(origins = "*")
    public Publisher<Comment> saveCommentInArticle(@PathVariable("name") String name, @PathVariable("articleName") String articleName,@RequestBody Comment comment){
        return service.saveCommentInArticle(name, articleName, comment);
    }


    @PostMapping(value = "{name}/articles")
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "*")
    public Publisher<Article> addArticleInCategory(@PathVariable("name") String name,@RequestBody Article article){
        return service.saveArticleInCategory(name,article);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "*")
    public Publisher<Category> saveCategory( @RequestBody Category category){
        return service.saveCategory(category);
    }

}
