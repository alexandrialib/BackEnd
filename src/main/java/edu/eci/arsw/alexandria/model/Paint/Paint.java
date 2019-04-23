package edu.eci.arsw.alexandria.model.Paint;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Data
public class Paint {
    private LocalDateTime lastModify;
    private String version;
    private ArrayList < Object > objects;
    private String background;
}
