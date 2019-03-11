package edu.eci.arsw.alexandria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id ;

    private String title;
    private String content;

    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }

    @ManyToOne
    @JsonIgnoreProperties("articles")
    private Category category;
//    private List<Version> past;
//    private Version actual;


}
