
package com.mycompany.pan.api.servicios;

import com.mycompany.pan.api.modelos.*;
import com.mycompany.pan.api.repositorios.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CanjeServ {

    private final CanjeRepo canjeRepo;
    private final ClienteRepo clienteRepo;
    private final RecomRepo recomRepo;
    private final InventarioRepo inventarioRepo;
    private final MovInvRep movInvRep;

    public CanjeServ(
            CanjeRepo canjeRepo,
            ClienteRepo clienteRepo,
            RecomRepo recomRepo,
            InventarioRepo inventarioRepo,
            MovInvRep movInvRep) {

        this.canjeRepo = canjeRepo;
        this.clienteRepo = clienteRepo;
        this.recomRepo = recomRepo;
        this.inventarioRepo = inventarioRepo;
        this.movInvRep = movInvRep;
    }

    public List<Canje> listarTodos() {
        return canjeRepo.findAll();
    }

    public Canje buscarPorId(int id) {
        return canjeRepo.findById(id).orElse(null);
    }

    @Transactional
    public Canje realizarCanje(int idCliente, int idRecompensa, int cantidad) {

        // Validar cantidad
        if (cantidad <= 0) {
            throw new RuntimeException(
                    "La cantidad debe ser mayor que cero");
        }

        // Buscar cliente
        Cliente cliente = clienteRepo.findById(idCliente)
                .orElseThrow(() -> new RuntimeException(
                        "El cliente no existe"));

        // Buscar recompensa
        Recom recompensa = recomRepo.findById(idRecompensa)
                .orElseThrow(() -> new RuntimeException(
                        "La recompensa no existe"));

        // Validar que la recompensa esté activa
        if (recompensa.getActivo() != 1) {
            throw new RuntimeException(
                    "La recompensa no está activa");
        }

        // Calcular puntos necesarios
        int puntosTotales =
                recompensa.getPuntosNecesarios() * cantidad;

        // Validar puntos del cliente
        if (cliente.getPuntos() < puntosTotales) {
            throw new RuntimeException(
                    "El cliente no tiene suficientes puntos");
        }

        // Buscar inventario del artículo asociado
        int idmenu = recompensa.getMenu().getIdmenu();

        Inventario inventario = inventarioRepo
                .findByMenu_Idmenu(idmenu)
                .orElseThrow(() -> new RuntimeException(
                        "No existe inventario para este artículo"));

        // Validar existencias
        if (inventario.getUnidades() < cantidad) {
            throw new RuntimeException(
                    "No hay suficientes unidades disponibles");
        }

        // Descontar puntos
        cliente.setPuntos(cliente.getPuntos() - puntosTotales);
        clienteRepo.save(cliente);

        // Descontar inventario
        inventario.setUnidades(
                inventario.getUnidades() - cantidad);

        inventarioRepo.save(inventario);

        // Registrar movimiento de inventario
        MovimientoInventario movimiento =
                new MovimientoInventario();

        movimiento.setMenu(recompensa.getMenu());
        movimiento.setCantidad(cantidad);
        movimiento.setTipo("SALIDA_CANJE");
        movimiento.setFecha(LocalDateTime.now());

        movInvRep.save(movimiento);

        // Registrar historial del canje
        Canje canje = new Canje();

        canje.setCliente(cliente);
        canje.setRecompensa(recompensa);
        canje.setCantidad(cantidad);
        canje.setPuntosUtilizados(puntosTotales);
        canje.setFecha(LocalDateTime.now());

        return canjeRepo.save(canje);
    }
}
