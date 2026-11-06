package com.mycompany.pan.api.servicios;

import com.mycompany.pan.api.modelos.Pago;
import com.mycompany.pan.api.modelos.Pedido;
import com.mycompany.pan.api.modelos.efectivoPagoDTO;
import com.mycompany.pan.api.repositorios.DetallePedidoRepo;
import com.mycompany.pan.api.repositorios.InventarioRepo;
import com.mycompany.pan.api.repositorios.MovInvRep;
import com.mycompany.pan.api.modelos.Inventario;
import com.mycompany.pan.api.modelos.MovimientoInventario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.pan.api.repositorios.PagoRepo;
import com.mycompany.pan.api.repositorios.PedidoRepo;
import com.mycompany.pan.api.repositorios.ClienteRepo;

import org.springframework.stereotype.Service;

@Service
public class PagoServicio {
    
    private final ClienteRepo clienteRepo;
    private final PagoRepo pagoRepo;
    private final PedidoRepo pedidoRepo;
    private final InventarioRepo inventarioRepo;
    private final DetallePedidoRepo detallePedidoRepo;
    private final MovInvRep movInvRep;
    
    public PagoServicio(
        PagoRepo pagoRepo,
        PedidoRepo pedidoRepo,
        InventarioRepo inventarioRepo,
        DetallePedidoRepo detallePedidoRepo,
        MovInvRep movInvRep,
        ClienteRepo clienteRepo) {

        this.pagoRepo = pagoRepo;
        this.pedidoRepo = pedidoRepo;
        this.inventarioRepo = inventarioRepo;
        this.detallePedidoRepo = detallePedidoRepo;
        this.movInvRep = movInvRep;
        this.clienteRepo = clienteRepo;
    }
    
    @Transactional
public Pago pagarEnEfectivo(efectivoPagoDTO datos) {

    Pedido pedido = pedidoRepo.findById(datos.getIdpedido())
            .orElseThrow(() -> new RuntimeException(
                    "El pedido no existe"));

    if (!"PENDIENTE".equalsIgnoreCase(pedido.getEstado())) {
        throw new RuntimeException(
                "El pedido no está pendiente de pago");
    }
    if (pagoRepo.existsByPedido_IdpedidoAndEstado(
        datos.getIdpedido(), "APROBADO")) {

        throw new RuntimeException(
            "Este pedido ya tiene un pago aprobado.");
}
    
var detalles = detallePedidoRepo.findByPedido_Idpedido(
        datos.getIdpedido()
);

    if (detalles.isEmpty()) {
        throw new RuntimeException(
            "El pedido no contiene artículos.");
    }

    for (var detalle : detalles) {

        int idmenu = detalle.getMenu().getIdmenu();

        Inventario inventario = inventarioRepo
            .findByMenu_Idmenu(idmenu)
            .orElseThrow(() -> new RuntimeException(
                    "No existe inventario para el artículo: " + idmenu
            ));

        if (inventario.getUnidades() < detalle.getCantidad()) {
            throw new RuntimeException(
                    "Existencias insuficientes para: "
                    + detalle.getMenu().getNombre()
            );
        }
    }

    if (datos.getMontoRecibido() == null
            || datos.getMontoRecibido().compareTo(BigDecimal.ZERO) <= 0) {

        throw new RuntimeException(
                "Monto inválido!");
    }

    BigDecimal total = pedido.getTotalPrecio();

    if (datos.getMontoRecibido().compareTo(total) < 0) {
        throw new RuntimeException(
                "Pobre! El total es Q" + total);
    }

    BigDecimal cambio = datos.getMontoRecibido().subtract(total);

    Pago pago = new Pago();

    pago.setPedido(pedido);
    pago.setMetodo("EFECTIVO");
    pago.setMontoRecibido(datos.getMontoRecibido());
    pago.setCambio(cambio);
    pago.setEstado("APROBADO");
    pago.setReferencia(null);
    pago.setFecha(LocalDateTime.now());
    
    // Descontar existencias y registrar movimientos
    for (var detalle : detalles) {

        int idmenu = detalle.getMenu().getIdmenu();

        Inventario inventario = inventarioRepo
            .findByMenu_Idmenu(idmenu)
            .orElseThrow(() -> new RuntimeException(
                    "No existe inventario para el artículo: " + idmenu
            ));

        int cantidadVendida = detalle.getCantidad();

        inventario.setUnidades(
            inventario.getUnidades() - cantidadVendida
        );
        
        inventarioRepo.save(inventario);

        MovimientoInventario movimiento = new MovimientoInventario();

        movimiento.setMenu(detalle.getMenu());
        movimiento.setCantidad(cantidadVendida);
        movimiento.setTipo("SALIDA_VENTA");
        movimiento.setFecha(LocalDateTime.now());

        movInvRep.save(movimiento);
        }
    
        pago = pagoRepo.save(pago);
        
        // Calcular los puntos obtenidos
int puntosGanados = 0;

for (var detalle : detalles) {

    int cantidad = detalle.getCantidad();
    int individual = detalle.getMenu().getIndividual();

    if (individual == 1) {
        puntosGanados += cantidad * 2;
    } else {
        puntosGanados += cantidad * 8;
    }
}

        Pedido pedidoActual = pedidoRepo.findById(datos.getIdpedido())
            .orElseThrow(() -> new RuntimeException(
                "No se pudo recuperar el pedido"));

            var cliente = pedidoActual.getCliente();

        cliente.setPuntos(cliente.getPuntos() + puntosGanados);

        clienteRepo.save(cliente);

        pedido.setEstado("PAGADO");
        pedidoRepo.save(pedido);

        return pago;
    }
    
}