package edu.eci.arsw.alexandria.model.Editor;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Editor {

    @Id
    private String id;

    @Builder.Default
    private List<String> text = Arrays.asList("");

    @Builder.Default
    private AtomicInteger version= new AtomicInteger(0);

    @Builder.Default
    private List<ByChar> location = new ArrayList<>();

    @Builder.Default
    private LocalDateTime lastEdit = LocalDateTime.now();

    @Builder.Default
    private String lastUser = "";
}
