package edu.eci.arsw.alexandria.config;


import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import com.mongodb.reactivestreams.client.MongoClient;


@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
@EnableReactiveMongoRepositories
public class MongoDBConfig extends AbstractReactiveMongoConfiguration {

    private final Environment environment;
    public MongoDBConfig(Environment environment){
        this.environment = environment;
    }


    @Override
    protected String getDatabaseName() {
        return "reactive-mongo";
    }

    @Override
    @Bean
    @DependsOn("embeddedMongoServer")
    public MongoClient reactiveMongoClient() {
        int port = environment.getProperty("local.mongo.port", Integer.class);
        return MongoClients.create(String.format("mongodb://localhost:%d", port));
    }
}
