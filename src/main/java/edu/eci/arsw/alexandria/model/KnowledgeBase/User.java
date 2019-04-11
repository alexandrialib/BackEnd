package edu.eci.arsw.alexandria.model.KnowledgeBase;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data // <-------------- con esta hermosa de etiqueta se ponen :v  no 
@Document
@NoArgsConstructor
@AllArgsConstructor
public class User extends Registration{

  @Id
  private String id;
  private String name;
  private String lastName;
  private String email;
  private String userName;
  private String password;
  private List<String> roles;

}

