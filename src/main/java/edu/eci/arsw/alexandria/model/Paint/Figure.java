package edu.eci.arsw.alexandria.model.Paint;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.*;
import lombok.Data;

@Data
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Path.class, name = "path"),
        @JsonSubTypes.Type(value = Circle.class, name = "circle"),
        @JsonSubTypes.Type(value = Rectangle.class, name = "rect"),
        @JsonSubTypes.Type(value = Text.class, name = "i-text"),
        @JsonSubTypes.Type(value = Line.class, name = "line")
})
public abstract class Figure {
    private String type;
    private String version;
    private String originX;
    private String originY;
    private float left;
    private float top;
    private float width;
    private float height;
    private String fill;
    private String stroke;
    private float strokeWidth;
    private String strokeDashArray = null;
    private String strokeLineCap;
    private String strokeLineJoin;
    private float strokeMiterLimit;
    private float scaleX;
    private float scaleY;
    private float angle;
    private boolean flipX;
    private boolean flipY;
    private float opacity;
    private String shadow = null;
    private boolean visible;
    private String clipTo = null;
    private String backgroundColor;
    private String fillRule;
    private String paintFirst;
    private String globalCompositeOperation;
    private String transformMatrix = null;
    private float skewX;
    private float skewY;
}
