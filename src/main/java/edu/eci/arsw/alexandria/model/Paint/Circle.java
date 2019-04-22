package edu.eci.arsw.alexandria.model.Paint;

import lombok.Data;

@Data
public class Circle extends Figure{
    private float radius;
    private float startAngle;
    private float endAngle;

}
