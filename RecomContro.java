package com.mycompany.pan.api.controladores;

import com.mycompany.pan.api.modelos.Recom;
import com.mycompany.pan.api.servicios.RecomServ;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recompensas")
public class RecomContro {

    private final RecomServ recomServ;

    public RecomContro(RecomServ recomServ) {
        this.recomServ = recomServ;
    }

    @GetMapping
    public List<Recom> listarTodos() {
        return recomServ.listarTodos();
    }

    @GetMapping("/{id}")
    public Recom buscarPorId(@PathVariable int id) {
        return recomServ.buscarPorId(id);
    }

    @PostMapping
    public Recom guardar(@RequestBody Recom recompensa) {
        return recomServ.guardar(recompensa);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        recomServ.eliminar(id);
    }
}