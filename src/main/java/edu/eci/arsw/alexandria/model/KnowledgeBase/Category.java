package edu.eci.arsw.alexandria.model.KnowledgeBase;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Document
public class Category {
    @Id
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private ObjectId id = new ObjectId();

    @NonNull private String name;
    private List<Article> articles = new ArrayList<>();
//    private List<Category> subCategories = new ArrayList<>();
}
