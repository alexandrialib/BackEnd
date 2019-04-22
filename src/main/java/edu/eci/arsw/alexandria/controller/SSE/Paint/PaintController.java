package edu.eci.arsw.alexandria.controller.SSE.Paint;


import edu.eci.arsw.alexandria.model.Paint.Figure;
import edu.eci.arsw.alexandria.model.Paint.Paint;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sse/paints")
public class PaintController {



    @GetMapping
    public void getPaints(){

    }

    @GetMapping("{id}")
    public void getPaints(@PathVariable String id){

    }

    @PostMapping
    public void savePaint(@RequestBody Paint paint){
        System.out.println(paint);
    }

    @PostMapping("figure")
    public Figure savePaint(@RequestBody Figure paint){
        System.out.println(paint);
        return paint;
    }
}
