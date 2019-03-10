package edu.eci.arsw.alexandria.entities;

import lombok.*;
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
    private String id;

    @NonNull private String name;

    private List<Article> articles = new ArrayList<>();
//    private List<Category> subCategories = new ArrayList<>();
}
