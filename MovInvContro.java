
package com.mycompany.pan.api.controladores;

import com.mycompany.pan.api.modelos.MovimientoInventario;
import com.mycompany.pan.api.servicios.MonInvSer;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimientos-inventario")
public class MovInvContro {

    private final MonInvSer servicio;

    public MovInvContro(MonInvSer servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<MovimientoInventario> listarTodos() {
        return servicio.listarTodos();
    }

    @GetMapping("/{id}")
    public MovimientoInventario buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id);
    }
}
