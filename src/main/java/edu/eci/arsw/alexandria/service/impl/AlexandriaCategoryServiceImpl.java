package edu.eci.arsw.alexandria.service.impl;

import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Comment;
import edu.eci.arsw.alexandria.repositories.CategoryRepository;
import edu.eci.arsw.alexandria.repositories.UserRepository;
import edu.eci.arsw.alexandria.service.AlexandriaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlexandriaCategoryServiceImpl implements AlexandriaCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Flux<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Category> getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    @Override
    public Flux<Article> getCategoryArticles(String name) {
        return categoryRepository.getCategoryByName(name).flatMapIterable(x -> x.getArticles());
    }

    @Override
    public Mono<Article> getCategoryArticleByName(String name,String articleName) {
        return categoryRepository.getCategoryByName(name).flatMap(x -> (Mono.just(x.getArticles().stream().filter(y -> y.getTitle().equals(articleName)).findFirst().orElse(null))));
    }

    @Override
    public Flux<Comment> getCategoryArticleByNameComments(String name, String article) {
        return getCategoryArticleByName(name, article).flatMapIterable(x -> x.getComments());
    }

    @Override
    public Flux<Comment> saveCommentInArticle(String category, String article, Comment comment) {
        userRepository.findByUsername(comment.getAuthor().getUsername()).subscribe(comment::setAuthor);
        return categoryRepository.getCategoryByName(category).flatMap(x -> {
            x.getArticles().stream().filter(a -> a.getTitle().equals(article))
                    .findFirst().orElse(null).AddComment(comment);
            return Mono.just(x);
        }).flatMap(this.categoryRepository::save)
                .flatMapIterable(x -> x.getArticles().stream()
                        .filter(a -> a.getTitle().equals(article))
                        .findFirst().orElse(null).getComments());
    }

    @Override
    public Mono<Category> saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Flux<Article> saveArticleInCategory(String name,Article article) {
        return categoryRepository.getCategoryByName(name).flatMap(x -> {
            x.addArticle(article);
            return Mono.just(x);
        }).flatMap(this.categoryRepository::save).flatMapIterable(x -> x.getArticles());
    }
}
