package edu.eci.arsw.alexandria.persistence;

import edu.eci.arsw.alexandria.entities.Article;
import edu.eci.arsw.alexandria.entities.Category;

import java.util.List;

public interface Persistence{

    public List<Article> getAllArticles();

    public List<Category> getAllCategory();

    public Article getArticleByName(String name);

    public Category getCategoryByName(String name);

    void saveArticle(Article article, String category);

    public void saveCategory(Category category);
}
