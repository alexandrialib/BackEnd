package edu.eci.arsw.alexandria.model.KnowledgeBase;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@NoArgsConstructor
@RequiredArgsConstructor
public class Category {

    @Id
    private String id;

    @NonNull private String name;

    private List<Article> articles = new ArrayList<>();

    public void addArticle(Article article){
        articles.add(article);
    }

}
