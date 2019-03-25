package edu.eci.arsw.alexandria.model.KnowledgeBase;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Article {

    @Id
    private String id;

    @NonNull private String title;
    @NonNull private String content;

//    private Category category;
//    private List<Version> past;
//    private Version actual;


}
