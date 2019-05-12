package edu.eci.arsw.alexandria.model.KnowledgeBase;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Sender;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
@NoArgsConstructor
@RequiredArgsConstructor
public class Category {

    @Id
    private String id;

    @NonNull private String name;

    private List<Article> articles = new ArrayList<>();

    public void addArticle(Article article){
        articles.add(article);
    }

    public void notificar() throws MailjetException, MailjetSocketTimeoutException {
    MailjetRequest request;
    MailjetResponse response;
    client = new MailjetClient(System.getenv("2c3e6af59c1e14ddb323c4c362817429"), System.getenv("919b7d494be472cc61b3a8e44a1461a8"), new ClientOptions("v3.1"));
    request = new MailjetRequest(Emailv31.resource)
          .property(Emailv31.MESSAGES, new JSONArray()
              .put(new JSONObject()
                  .put(Emailv31.Message.FROM, new JSONObject()
                      .put("Email", "pilot@mailjet.com")
                      .put("Name", "Mailjet Pilot"))
                  .put(Emailv31.Message.TO, new JSONArray()
                      .put(new JSONObject()
                          .put("Email", "passenger1@mailjet.com")
                          .put("Name", "passenger 1")))
                  .put(Emailv31.Message.SUBJECT, "Your email flight plan!")
                  .put(Emailv31.Message.TEXTPART, "Dear passenger 1, welcome to Mailjet! May the delivery force be with you!")
                  .put(Emailv31.Message.HTMLPART, "<h3>Dear passenger 1, welcome to <a href='https://www.mailjet.com/'>Mailjet</a>!</h3><br />May the delivery force be with you!")));
    response = client.post(request);
    System.out.println(response.getStatus());
    System.out.println(response.getData());
  }
}
