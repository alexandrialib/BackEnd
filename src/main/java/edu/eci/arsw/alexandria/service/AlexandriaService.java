package edu.eci.arsw.alexandria.service;


import edu.eci.arsw.alexandria.entities.Article;
import edu.eci.arsw.alexandria.entities.Category;

import java.util.List;


public interface AlexandriaService {

    public List<Article>  getAllArticles();

    public List<Category> getAllCategories();

    public Category getCategoryByName(String name);

    public Article getArticleByName(String name);

    public List<Article> getArticlesByAuthor(String author);

    public void saveCategory(Category category);
}
