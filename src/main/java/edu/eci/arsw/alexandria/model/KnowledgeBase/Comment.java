package edu.eci.arsw.alexandria.model.KnowledgeBase;

import edu.eci.arsw.alexandria.repositories.UserRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Comment {

    @NonNull
    private User author;
    @NonNull
    private String content;
    private LocalDateTime date = LocalDateTime.now();
    private Set<User> likes = new HashSet<>();

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
