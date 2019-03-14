package edu.eci.arsw.alexandria.service.implement;

import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import edu.eci.arsw.alexandria.repositories.CategoryRepository;
import edu.eci.arsw.alexandria.service.AlexandriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AlexandriaServiceImpl implements AlexandriaService {

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
        return categoryRepository.getCategoryByName(name).flatMapIterable(x -> x.getArticles());
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void saveArticleInCategory(String name,Article article) {
//        categoryRepository.getCategoryByName(name).flatMapIterable(x ->x.getArticles());
    }
}
