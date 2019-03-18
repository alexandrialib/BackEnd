package edu.eci.arsw.alexandria.model.Editor;

import edu.eci.arsw.alexandria.model.KnowledgeBase.User;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
public abstract class Location {

    @NonNull
    private User user;
    private int row,column;
    private int[] range;
    @NonNull
    private Editor editor;

    public abstract int[] getBlockedRange();
}
