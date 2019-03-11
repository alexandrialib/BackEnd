package edu.eci.arsw.alexandria.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(exclude = "articles")
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id ;

    private String name;

    public Category(String name,Article... articles){
        this.name = name;
        this.articles = Arrays.stream(articles).collect(Collectors.toList());
        this.articles.forEach( x-> x.setCategory(this));
    }

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("category")
    private List<Article> articles = new ArrayList<>();
//    private List<Category> subCategories = new ArrayList<>();
}
