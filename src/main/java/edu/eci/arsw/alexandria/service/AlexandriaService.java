package edu.eci.arsw.alexandria.service;


import edu.eci.arsw.alexandria.entities.Article;
import edu.eci.arsw.alexandria.entities.Category;

import java.util.List;


public interface AlexandriaService {

    List<Category> getAllCategories();

    Category getCategoryByName(String name);

    List<Article> getCategoryArticles(String name);

    void saveCategory(Category category);

    void saveArticleInCategory(String name, Article article);
}
