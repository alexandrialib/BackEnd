package edu.eci.arsw.alexandria.model.Paint;

import lombok.Data;

@Data
public class Text extends Figure{
    private String text;
    private float fontSize;
    private String fontWeight;
    private String fontFamily;
    private String fontStyle;
    private float lineHeight;
    private boolean underline;
    private boolean overline;
    private boolean linethrough;
    private String textAlign;
    private String textBackgroundColor;
    private float charSpacing;
    Object StylesObject;
}
