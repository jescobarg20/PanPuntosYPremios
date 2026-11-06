
package com.mycompany.pan.api.servicios;

import com.mycompany.pan.api.modelos.MovimientoInventario;
import com.mycompany.pan.api.repositorios.MovInvRep;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MonInvSer {

    private final MovInvRep repositorio;

    public MonInvSer(MovInvRep repositorio) {
        this.repositorio = repositorio;
    }

    public List<MovimientoInventario> listarTodos() {
        return repositorio.findAll();
    }

    public MovimientoInventario buscarPorId(int id) {
        return repositorio.findById(id).orElse(null);
    }

    public MovimientoInventario guardar(MovimientoInventario movimiento) {
        return repositorio.save(movimiento);
    }
}
