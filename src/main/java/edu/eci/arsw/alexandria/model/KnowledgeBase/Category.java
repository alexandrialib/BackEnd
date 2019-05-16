package edu.eci.arsw.alexandria.model.KnowledgeBase;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;
import com.sendgrid.*;
import java.io.IOException;

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
        notificar(article);
    }
    public void subscribe(User sub){ subs.add(sub); }

    public void notificar(Article a) {
        System.err.println("category: " + name + " has a new article called " + a.getTitle());
        //for(User u: subs){
            Email from = new Email("alexandrialib@mail.com");
            String subject = "New Article!!!";
            Email to = new Email("alejoguzm07@gmail.com");
            //Email to = new Email(u.getEmail());
            //Content content = new Content("text/plain", u.getName() + ", an article was added to category " + name + "if you want to know about " + a.getTitle() + "click here: <a href='https://alexandria-lib-front.herokuapp.com/categories/" + name +"/articles/" + a.getTitle());
            Content content = new Content("text/plain", "Alejandro, an article was added to category " + name + "if you want to know about " + a.getTitle() + "click here: <a href='https://alexandria-lib-front.herokuapp.com/categories/" + name +"/articles/" + a.getTitle());
            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
            Request request = new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        //}

    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<User> getSubs() {
        return subs;
    }
}
