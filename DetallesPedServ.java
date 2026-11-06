
package com.mycompany.pan.api.servicios;

import com.mycompany.pan.api.modelos.Detalles_de_Pedidos;
import com.mycompany.pan.api.repositorios.DetallePedidoRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DetallesPedServ {

    private final DetallePedidoRepo detallePedidoRepo;

    public DetallesPedServ(DetallePedidoRepo detallePedidoRepo) {
        this.detallePedidoRepo = detallePedidoRepo;
    }

    public List<Detalles_de_Pedidos> listarTodos() {
        return detallePedidoRepo.findAll();
    }

    public Optional<Detalles_de_Pedidos> buscarPorId(int id) {
        return detallePedidoRepo.findById(id);
    }

    public Detalles_de_Pedidos guardar(Detalles_de_Pedidos detalle) {
        return detallePedidoRepo.save(detalle);
    }

    public void eliminar(int id) {
        detallePedidoRepo.deleteById(id);
    }
}
