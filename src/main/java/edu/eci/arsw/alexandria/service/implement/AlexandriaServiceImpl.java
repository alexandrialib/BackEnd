package edu.eci.arsw.alexandria.service.implement;

import edu.eci.arsw.alexandria.entities.Article;
import edu.eci.arsw.alexandria.entities.Category;
import edu.eci.arsw.alexandria.repositories.ArticleRepository;
import edu.eci.arsw.alexandria.repositories.CategoryRepository;
import edu.eci.arsw.alexandria.service.AlexandriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlexandriaServiceImpl implements AlexandriaService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    @Override
    public Article getArticleByName(String name) {
        return articleRepository.getArticleByTitle(name);
    }

    @Override
    public List<Article> getArticlesByAuthor(String author) {
        return null;
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
