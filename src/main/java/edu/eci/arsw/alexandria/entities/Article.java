package edu.eci.arsw.alexandria.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private String id;

    @NonNull private String title;
    @NonNull private String content;

    private Category category;
//    private List<Version> past;
//    private Version actual;


}
