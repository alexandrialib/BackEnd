package edu.eci.arsw.alexandria.config;


import java.util.ArrayList;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import edu.eci.arsw.alexandria.model.Editor.Editor;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Article;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Category;
import edu.eci.arsw.alexandria.model.KnowledgeBase.Comment;
import edu.eci.arsw.alexandria.model.KnowledgeBase.User;
import edu.eci.arsw.alexandria.repositories.CategoryRepository;
import edu.eci.arsw.alexandria.repositories.EditorRepository;
import edu.eci.arsw.alexandria.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer {
    private Lorem loremIpsum = LoremIpsum.getInstance();

    private final EditorRepository editors;
    private final CategoryRepository categories;
    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;

    DataInitializer(EditorRepository editors, CategoryRepository categories, UserRepository users, PasswordEncoder passwordEncoder) {
        this.editors = editors;
        this.categories = categories;
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }


    @EventListener(value = ContextRefreshedEvent.class)
    public void init() {
        initUsers();
        initCategories();
        initEditors();
    }

    private void initUsers() {
        log.info("start users users  ...");
        User u1 = User.builder().username("admin").password(passwordEncoder.encode("admin")).build();
        u1.addRole("ADMIN");
        User u2 = User.builder().username("user").password(passwordEncoder.encode("user")).build();
        this.users.deleteAll();
        this.users.save(u1).subscribe();
        this.users.save(u2).subscribe();
    }

    private void initCategories() {
        log.info("start data categories  ...");
        User user = users.findByUsername("admin").block();
        ArrayList<Category> list = new ArrayList<>();
        categories.deleteAll();
        list.add(new Category("Sort"));
        list.add(new Category("Graph theory"));
        list.add(new Category("Data structures"));
        list.get(0).addArticle((new Article("Quick", loremIpsum.getWords(200), user)));
        list.get(0).getArticles().get(0).AddComment(new Comment(user, "comment example"));
        list.get(0).addArticle((new Article("Selection", loremIpsum.getWords(200), user)));
        list.get(0).addArticle((new Article("Bubble", loremIpsum.getWords(200), user)));
        list.get(0).addArticle((new Article("Merge", loremIpsum.getWords(200), user)));
        list.get(1).addArticle((new Article("TopoSort", loremIpsum.getWords(200), user)));
        list.get(1).addArticle((new Article("MST", loremIpsum.getWords(200), user)));
        list.get(1).addArticle((new Article("Dijkstra", loremIpsum.getWords(200), user)));
        categories.deleteAll().subscribe();
        categories.saveAll(list).subscribe();
    }

    private void initEditors(){
        log.info("start data editors ........");
        ArrayList<Editor> s = new ArrayList<>();
        s.add(new Editor());
        s.get(0).setId("5c992f0922a4ae1086a46fd5");
        editors.saveAll(s).subscribe();
    }

}

