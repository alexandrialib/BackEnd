package edu.eci.arsw.alexandria.service.impl;

        import edu.eci.arsw.alexandria.repositories.BlackboardRepository;
        import edu.eci.arsw.alexandria.service.AlexandriaBlackboardService;
        import org.springframework.stereotype.Service;
        import reactor.core.publisher.Flux;
        import reactor.core.publisher.Mono;

        import java.util.List;
        import java.util.Map;
        import java.util.concurrent.ConcurrentHashMap;

@Service
public class AlexandriaBlackboardServiceImpl implements AlexandriaBlackboardService {

    private final BlackboardRepository repository;

    //private Map<String, BlackboardListenerReactive> reactiveMap = new ConcurrentHashMap<>();

    public AlexandriaBlackboardServiceImpl(BlackboardRepository repository) {
        this.repository = repository;
    }
}
