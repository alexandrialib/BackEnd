package edu.eci.arsw.alexandria;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import edu.eci.arsw.alexandria.model.KnowledgeBase.User;
import edu.eci.arsw.alexandria.repositories.CategoryRepository;
import edu.eci.arsw.alexandria.repositories.EditorRepository;
import edu.eci.arsw.alexandria.repositories.UserRepository;

import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.UUID;

@SpringBootApplication  
public class AlexandriaApplication {
    Lorem loremIpsum = LoremIpsum.getInstance();
    public static void main(String[] args) {
        SpringApplication.run(AlexandriaApplication.class, args);
    }

    @Bean
    public CommandLineRunner setupCategory(CategoryRepository categoryRepository) {
        return (args) -> {
            ArrayList<Category> s = new ArrayList<>();
            s.add(new Category("Sort"));
            s.add(new Category("Graph theory"));
            s.add(new Category("Data structures"));
            s.get(0).getArticles().add((new Article("Quick", loremIpsum.getWords(10))));
            s.get(0).getArticles().add((new Article("Selection", loremIpsum.getWords(10))));
            s.get(0).getArticles().add((new Article("Bubble", loremIpsum.getWords(10))));
            s.get(0).getArticles().add((new Article("Merge", loremIpsum.getWords(10))));
            s.get(1).getArticles().add((new Article("TopoSort", loremIpsum.getWords(10))));
            s.get(1).getArticles().add((new Article("MST", loremIpsum.getWords(10))));
            s.get(1).getArticles().add((new Article("Dijkstra", loremIpsum.getWords(10))));
            categoryRepository.saveAll(s).blockLast();
        };
    }

    @Bean
    public CommandLineRunner setupEditor(EditorRepository editorRepository) {
        return (args) -> {
            ArrayList<Editor> s = new ArrayList<>();
            s.add(new Editor());
            editorRepository.saveAll(s).blockLast();
        };
    }

    @Bean
    public CommandLineRunner setupUser(UserRepository userRepository) {
        return (args) -> {
            ArrayList<User> s = new ArrayList<>();
            ArrayList<String> rls = new ArrayList<>();
            rls.add("ROLE_ADMINISTRATOR");
            rls.add("ROLE_TEACHER");
            rls.add("ROLE_MONITOR");
            rls.add("ROLE_STUDENT");
            s.add(new User(UUID.randomUUID().toString(),
            "Admin",
            "Admin",
            "Admin@Alexandrialib.com",
            "Admin",
            "$2y$08$g1hTOsRRWIk8.Wlm3YywXORi2c8YvwuMFhglmW5KtAoNUMSfjr0iC",
            rls));
            userRepository.saveAll(s).blockLast();
        };
    }
}


//revise la clase que le digo: mongouserdetailsservice

//Ya compila pero se totea por algun motivo!
//cuando se totea?

//org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'securityConfiguration': Unsatisfied dependency expressed through field 'userDetailsService'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'mongoUserDetailsService': Unsatisfied dependency expressed through field 'repository'; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'userRepository': Invocation of init method failed; nested exception is org.springframework.data.mapping.PropertyReferenceException: No property username found for type User! Did you mean 'userName'?
// es lo que le digo, esa clase esta con el constructor de usuario que no tenia casi info 
// Porque antes ud en vez del model.security.users tenia un user re X del Java :v lool
//Igual lo deje porque no se de eso :v jajajaja
//este import org.springframework.security.core.userdetails.User;
//en qué clase?  MongoUserDetailsService
//pruebe cambiandolo al del modelo y en el retun agregando el resto de atributos