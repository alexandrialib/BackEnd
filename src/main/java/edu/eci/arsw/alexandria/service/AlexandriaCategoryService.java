package edu.eci.arsw.alexandria.service;


import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface AlexandriaCategoryService {

    Flux<Category> getAllCategories();

    Mono<Category> getCategoryByName(String name);

    Flux<Article> getCategoryArticles(String name);

    Mono<Category> saveCategory(Category category);

    Flux<Article> saveArticleInCategory(String name, Article article);

    Article getCategoryArticleByName(String name, String article);
}
