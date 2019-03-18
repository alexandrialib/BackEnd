package edu.eci.arsw.alexandria.model.Editor;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Editor {

    @Id
    private ObjectId id  = new ObjectId();

    @NonNull
    private Text text;

    @NonNull
    private List<ByChar> location ;

}
