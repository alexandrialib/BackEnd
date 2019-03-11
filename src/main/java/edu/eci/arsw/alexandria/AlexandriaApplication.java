package edu.eci.arsw.alexandria;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import edu.eci.arsw.alexandria.entities.Article;
import edu.eci.arsw.alexandria.entities.Category;
import edu.eci.arsw.alexandria.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

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
            s.add(new Category("Sort",
                    new Article("Bubble",loremIpsum.getWords(10)),
                    new Article("Quick",loremIpsum.getWords(10))
            ));
            s.add(new Category("Graph theory"));
            s.add(new Category("Data structures"));
            categoryRepository.saveAll(s);
            //jdbc:h2:mem:testdb
        };
    }
}
