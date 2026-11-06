
package com.mycompany.pan.api.controladores;

import com.mycompany.pan.api.modelos.Canje;
import com.mycompany.pan.api.modelos.CanjeDTO;
import com.mycompany.pan.api.servicios.CanjeServ;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/canjes")
public class CanjeContro {

    private final CanjeServ canjeServ;

    public CanjeContro(CanjeServ canjeServ) {
        this.canjeServ = canjeServ;
    }

    @GetMapping
    public List<Canje> listarTodos() {
        return canjeServ.listarTodos();
    }

    @GetMapping("/{id}")
    public Canje buscarPorId(@PathVariable int id) {
        return canjeServ.buscarPorId(id);
    }

    @PostMapping
    public Canje realizarCanje(@RequestBody CanjeDTO datos) {

        return canjeServ.realizarCanje(
                datos.getIdCliente(),
                datos.getIdRecompensa(),
                datos.getCantidad()
        );
    }
}
