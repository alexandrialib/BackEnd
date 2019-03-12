package edu.eci.arsw.alexandria.model.KnowledgeBase;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private ObjectId id = new ObjectId();

    @NonNull private String title;
    @NonNull private String content;

//    private Category category;
//    private List<Version> past;
//    private Version actual;


}
