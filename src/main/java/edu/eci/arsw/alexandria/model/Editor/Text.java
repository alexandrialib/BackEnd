package edu.eci.arsw.alexandria.model.Editor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Text {

    @Id
    private ObjectId id = new ObjectId();
    private List<String> content = new ArrayList<>();

}
