package edu.eci.arsw.alexandria.service.implement;

import edu.eci.arsw.alexandria.entities.Article;
import edu.eci.arsw.alexandria.entities.Category;
import edu.eci.arsw.alexandria.persistence.Persistence;
import edu.eci.arsw.alexandria.service.AlexandriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlexandriaServiceImpl implements AlexandriaService {

    @Autowired
    private Persistence persistence;

    @Override
    public List<Article> getAllArticles() {
        return persistence.getAllArticles();
    }

    @Override
    public List<Category> getAllCategories() {
        return persistence.getAllCategory();
    }

    @Override
    public Category getCategoryByName(String name) {
        return persistence.getCategoryByName(name);
    }

    @Override
    public Article getArticleByName(String name) {
        return persistence.getArticleByName(name);
    }

    @Override
    public List<Article> getArticlesByAuthor(String author) {
        return null;
    }

    @Override
    public void saveCategory(Category category) {
        persistence.saveCategory(category);
    }
}
