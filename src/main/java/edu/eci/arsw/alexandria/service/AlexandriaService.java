package edu.eci.arsw.alexandria.service;


import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface AlexandriaService {

    Flux<Category> getAllCategories();

    Mono<Category> getCategoryByName(String name);

    Flux<Article> getCategoryArticles(String name);

    void saveCategory(Category category);

    void saveArticleInCategory(String name, Article article);
}
