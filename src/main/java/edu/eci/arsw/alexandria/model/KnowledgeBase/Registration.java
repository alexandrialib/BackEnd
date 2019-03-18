package edu.eci.arsw.alexandria.model.KnowledgeBase;

import lombok.Data;

import java.util.Date;

@Data
public abstract class Registration {
    private Date creationDate;

    public Registration(){
        creationDate = new Date();
    }
}
