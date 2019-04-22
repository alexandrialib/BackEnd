package edu.eci.arsw.alexandria.model.Paint;

import lombok.Data;

import java.util.ArrayList;


@Data
public class Paint {
    private String version;
    ArrayList < Object > draws;
    private String background;
}
