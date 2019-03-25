package edu.eci.arsw.alexandria.model.KnowledgeBase;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Article {

    @Id
    private String id;

    private String title;
    private String content;

    private Category category;
//    private List<Version> past;
//    private Version actual;


}
