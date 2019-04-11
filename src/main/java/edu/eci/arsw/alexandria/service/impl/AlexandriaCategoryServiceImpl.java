package edu.eci.arsw.alexandria.service.impl;

import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import edu.eci.arsw.alexandria.repositories.CategoryRepository;
import edu.eci.arsw.alexandria.service.AlexandriaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlexandriaCategoryServiceImpl implements AlexandriaCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


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
        return null;
    }

    @Override
    public Mono<Category> saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Flux<Article> saveArticleInCategory(String name,Article article) {
        return null;
    }
}
