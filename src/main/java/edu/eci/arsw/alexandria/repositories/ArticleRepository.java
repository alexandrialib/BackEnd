package edu.eci.arsw.alexandria.repositories;

import edu.eci.arsw.alexandria.entities.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends MongoRepository<Article, String> {

    Article getArticleByTitle(String title);

}
