package edu.eci.arsw.alexandria.repositories;

import edu.eci.arsw.alexandria.entities.Article;
import edu.eci.arsw.alexandria.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="articles")
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
