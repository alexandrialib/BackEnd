package edu.eci.arsw.alexandria.service;


import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Comment;
import edu.eci.arsw.alexandria.model.KnowledgeBase.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface AlexandriaCategoryService {

    Flux<Category> getAllCategories();

    Mono<Category> getCategoryByName(String name);

    Flux<Article> getCategoryArticles(String name);

    Mono<Category> saveCategory(Category category);

    Flux<Article> saveArticleInCategory(String name, Article article);

    Mono<Article> getCategoryArticleByName(String name, String article);

    Flux<Comment> getCategoryArticleByNameComments(String name,String article);

    Flux<Comment> saveCommentInArticle(String category, String article, Comment comment);

    Flux<User> subscribeToCategory(String category, User user);
}
