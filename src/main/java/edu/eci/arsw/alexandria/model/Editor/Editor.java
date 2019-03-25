package edu.eci.arsw.alexandria.model.Editor;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Editor {

    @Id
    private String id;

    @NonNull
    private Text text;

    @NonNull
    private List<ByChar> location ;

}
