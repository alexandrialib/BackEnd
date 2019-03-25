package edu.eci.arsw.alexandria;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import edu.eci.arsw.alexandria.model.Security.Users;
import edu.eci.arsw.alexandria.repositories.CategoryRepository;
import edu.eci.arsw.alexandria.repositories.EditorRepository;
import edu.eci.arsw.alexandria.repositories.UsersRepository;

import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

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
    public CommandLineRunner setupUser(UsersRepository usersRepository) {
        return (args) -> {
            ArrayList<Users> s = new ArrayList<>();
            ArrayList<String> rls = new ArrayList<>();
            rls.add("ROLE_ADMINISTRATOR");
            rls.add("ROLE_TEACHER");
            rls.add("ROLE_MONITOR");
            rls.add("ROLE_STUDENT");
            s.add(new Users(new ObjectId(), "pablito", "$2a$10$AjHGc4x3Nez/p4ZpvFDWeO6FGxee/cVqj5KHHnHfuLnIOzC5ag4fm",rls));
            usersRepository.saveAll(s).blockLast();
        };
    }
}
