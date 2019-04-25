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
        list.get(1).addArticle((new Article("MST", "In graph theory, a tree is a graph in which there is exactly one path between two nodes and all its nodes are connected. A minimum expansion tree is, as its name indicates, a tree that connects all the nodes of a network and that additionally is the most efficient existing way to do it, that is to say, the sum of its arcs is the least possible. To obtain the minimum expansion tree we are going to study the Kruskal algorithm, for which the graph must meet certain requirements: \n" +
                "- Not to be directed \n" +
                "- Have weights \n" +
                "- To be connected \n" +
                "\n" +
                "If the network fulfills the above conditions, you proceed with the algorithm, which has two phases: \n" +
                "1. Sort a list of arcs that represents the graph. \n" +
                "This algorithm requires the network to be represented by a list of arcs, in the form (u,v,w) since the requirements stipulated that the network must have weights. This list of arcs will be sorted according to their weight, this to ensure that the tree resulting from this algorithm will give us the least expensive way to connect the nodes.\n" +
                " \n" +
                "2. Create a dissyunto set and integrate the graph to this structure \n" +
                "Each node of the network will be represented as a disjointed set, one without children. The ordered arcs will be crossed in order to integrate the tree to this structure, joining the nodes of each crossed arc in order to stipulate that these two are already connected in the minimum expansion tree. If the nodes were already connected at the time of crossing them, this arch will be discarded, since there is already a less expensive way to connect them. \n" +
                "\n" +
                "This is the general way to find the MST by means of Kruskal's algorithmbut certain improvements can be added to this one. The number of edges of the tree is the number of nodes minus one, so we should stop going through the list of arcs, in order to make the algorithm more efficient, because if this condition was already met, we would be processing nodes that will always be connected.", user)));
        list.get(1).addArticle((new Article("Dijkstra", loremIpsum.getWords(200), user)));
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

