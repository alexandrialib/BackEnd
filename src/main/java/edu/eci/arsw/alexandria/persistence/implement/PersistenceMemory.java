package edu.eci.arsw.alexandria.persistence.implement;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import edu.eci.arsw.alexandria.entities.Article;
import edu.eci.arsw.alexandria.entities.Category;
import edu.eci.arsw.alexandria.persistence.Persistence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersistenceMemory implements Persistence {

    private Map<String, Category> db;

    public PersistenceMemory(){
        Lorem loremIpsum = LoremIpsum.getInstance();
        db = new HashMap<>();
        db.put("Sort",new Category("Sort"));
        db.put("Graph theory",new Category("Graph theory"));
        db.put("Data structures",new Category("Data structures"));

        db.get("Sort").getArticles().add(new Article("Merge sort", loremIpsum.getWords(1000)));
        db.get("Sort").getArticles().add(new Article("Selection sort",loremIpsum.getWords(1000)));
        db.get("Graph theory").getArticles().add(new Article("Toposort",loremIpsum.getWords(1000)));
        db.get("Graph theory").getArticles().add(new Article("MST",loremIpsum.getWords(1000)));

    }

    @Override
    public List<Article> getAllArticles() {
        List<Article> res = new ArrayList<>();
        for(String s: db.keySet()){
            res.addAll(db.get(s).getArticles());
        }
        return res;
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> res = new ArrayList<>();
        for(String s: db.keySet()){
            res.add(db.get(s));
        }
        return res;
    }

    @Override
    public Article getArticleByName(String name) {
        return getAllArticles().stream().filter(x -> x.getTitle().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Category getCategoryByName(String name) {
        return getAllCategory().stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public void saveArticle(Article article, String category) {
        db.get(category).getArticles().add(article);
    }

    @Override
    public void saveCategory(Category category) {
        db.put(category.getName(),category);
    }
}
