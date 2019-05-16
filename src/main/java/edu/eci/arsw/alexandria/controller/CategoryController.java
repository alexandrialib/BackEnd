package edu.eci.arsw.alexandria.controller;

import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Comment;
import edu.eci.arsw.alexandria.model.KnowledgeBase.User;
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
    public Publisher<Category> getAllCategories(){
        return service.getAllCategories();
    }

    @GetMapping(value = "{name}")
    public Publisher<Category> getCategoryByName(@PathVariable("name") String name){
        return service.getCategoryByName(name);
    }

    @GetMapping(value = "{name}/articles")
    public Publisher<Article> getAllCategoryArticles(@PathVariable("name") String name){
        return service.getCategoryArticles(name);
    }

    @GetMapping(value = "{name}/articles/{articleName}")
    public Publisher<Article> getAllCategoryArticles(@PathVariable("name") String name,@PathVariable("articleName") String articleName){
        return service.getCategoryArticleByName(name,articleName);
    }

    @GetMapping(value = "{name}/articles/{articleName}/comments")
    public Publisher<Comment> getAllCategoryArticlesComments(@PathVariable("name") String name, @PathVariable("articleName") String articleName){
        return service.getCategoryArticleByNameComments(name,articleName);
    }

    @PostMapping(value = "{name}/articles/{articleName}/comments")
    @PreAuthorize("hasRole('USER')")
    public Publisher<Comment> saveCommentInArticle(@PathVariable("name") String name, @PathVariable("articleName") String articleName,@RequestBody Comment comment){
        return service.saveCommentInArticle(name, articleName, comment);
    }


    @PostMapping(value = "{name}/articles")
    @PreAuthorize("hasRole('ADMIN')")
    public Publisher<Article> addArticleInCategory(@PathVariable("name") String name,@RequestBody Article article){
        System.err.println("category: " + name + " has a new article called " + article.getTitle());
        return service.saveArticleInCategory(name,article);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public Publisher<Category> saveCategory( @RequestBody Category category){
        return service.saveCategory(category);
    }

    @PostMapping(value = "{name}/subscribe")
    @PreAuthorize("hasRole('ADMIN')")
    public Publisher<User> subscribeToCategory(@PathVariable("name") String name, @RequestBody User user){
        return service.subscribeToCategory(name, user);
    }

}
