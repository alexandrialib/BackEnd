package edu.eci.arsw.alexandria.model.KnowledgeBase;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Article {

    @NonNull
    private String title;
    @NonNull
    private String content;
    @NonNull
    private User author;
    @NonNull
    private LocalDateTime localDateTime = LocalDateTime.now();

    private List<Comment> comments = new ArrayList<>();
    private Set<User> likes = new HashSet<>();

//    private List<Version> past;
//    private Version actual;

    public void AddComment(Comment comment){
        comments.add(comment);
    }


}
