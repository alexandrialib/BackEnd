package edu.eci.arsw.alexandria.controller;


import edu.eci.arsw.alexandria.service.AlexandriaBlackboardService;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/boards")
public class BlackboardController {

    private final AlexandriaBlackboardService service;

    public BlackboardController(AlexandriaBlackboardService service) {
        this.service = service;
    }


}
