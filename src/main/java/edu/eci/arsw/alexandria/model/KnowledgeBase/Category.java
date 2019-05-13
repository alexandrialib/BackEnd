package edu.eci.arsw.alexandria.model.KnowledgeBase;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;
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
    private List<User> subs = new ArrayList<>();

    public void addArticle(Article article){
        articles.add(article);
    }
    public void subscribe(User sub){
        subs.add(sub);
    }

    public void notificar(Article a) {
        for(User u: subs){
            MailjetRequest request;
            MailjetResponse response = null;
            MailjetClient client = new MailjetClient(System.getenv("2c3e6af59c1e14ddb323c4c362817429"), System.getenv("919b7d494be472cc61b3a8e44a1461a8"), new ClientOptions("v3.1"));
            request = new MailjetRequest(Emailv31.resource)
                    .property(Emailv31.MESSAGES, new JSONArray()
                            .put(new JSONObject()
                                    .put(Emailv31.Message.FROM, new JSONObject()
                                            .put("Email", "alexandrialib@mail.com")
                                            .put("Name", "Alexandria Lib"))
                                    .put(Emailv31.Message.TO, new JSONArray()
                                            .put(new JSONObject()
                                                    .put("Email", u.getEmail())
                                                    .put("Name", u.getUsername())))
                                    .put(Emailv31.Message.SUBJECT, "New Article")
                                    .put(Emailv31.Message.TEXTPART, u.getName() + ", an article was added to category " + name)
                                    .put(Emailv31.Message.HTMLPART, "<h3>Raider of the lost knowledge, if you want to know about " + a.getTitle() + "click here: <a href='https://alexandria-lib-front.herokuapp.com/categories/" + name +"/articles/" + a.getTitle() +"'></a>!</h3>")));
            try {
                response = client.post(request);
            } catch (MailjetException e) {
                e.printStackTrace();
            } catch (MailjetSocketTimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(response.getStatus());
            System.out.println(response.getData());
        }

    }

    public List<Article> getArticles() {
        return articles;
    }
}
