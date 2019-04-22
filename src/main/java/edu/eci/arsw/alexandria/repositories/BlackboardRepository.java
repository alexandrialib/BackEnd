package edu.eci.arsw.alexandria.repositories;

        import edu.eci.arsw.alexandria.model.Blackboard.Blackboard;
        import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
        import org.springframework.data.mongodb.repository.Tailable;
        import org.springframework.stereotype.Repository;
        import reactor.core.publisher.Flux;
        import reactor.core.publisher.Mono;

@Repository
public interface BlackboardRepository extends ReactiveMongoRepository<Blackboard, String> {
}
