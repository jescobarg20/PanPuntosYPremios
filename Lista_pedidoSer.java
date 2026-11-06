package com.mycompany.pan.api.servicios;

import com.mycompany.pan.api.repositorios.PedidoRepo;
import com.mycompany.pan.api.repositorios.ClienteRepo;
import com.mycompany.pan.api.repositorios.MenuRepo;
import com.mycompany.pan.api.repositorios.InventarioRepo;
import com.mycompany.pan.api.repositorios.DetallePedidoRepo;

import com.mycompany.pan.api.modelos.Cliente;
import com.mycompany.pan.api.modelos.Menu;
import com.mycompany.pan.api.modelos.Pedido;
import com.mycompany.pan.api.modelos.Inventario;
import com.mycompany.pan.api.modelos.Detalles_de_Pedidos;

import com.mycompany.pan.api.modelos.PedidoCompletoDTO;
import com.mycompany.pan.api.modelos.Creador_de_pedidosDTO;

import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Map;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

@Service
public class Lista_pedidoSer {

    private final PedidoRepo pedidoRepo;
    private final ClienteRepo clienteRepo;
    private final MenuRepo menuRepo;
    private final InventarioRepo inventarioRepo;
    private final DetallePedidoRepo detallePedidoRepo;

    public Lista_pedidoSer(
            PedidoRepo pedidoRepo,
            ClienteRepo clienteRepo,
            MenuRepo menuRepo,
            InventarioRepo inventarioRepo,
            DetallePedidoRepo detallePedidoRepo) {

        this.pedidoRepo = pedidoRepo;
        this.clienteRepo = clienteRepo;
        this.menuRepo = menuRepo;
        this.inventarioRepo = inventarioRepo;
        this.detallePedidoRepo = detallePedidoRepo;
    }
    
    @Transactional
public Pedido crearPedido(PedidoCompletoDTO datos) {

    Cliente cliente = clienteRepo.findById(datos.getIdCliente())
            .orElseThrow(() -> new RuntimeException(
                    "El cliente no existe"));


    Map<Integer, Integer> articulosAgrupados = new LinkedHashMap<>();

    for (Creador_de_pedidosDTO articulo : datos.getArticulos()) {

        if (articulo.getCantidad() <= 0) {
            throw new RuntimeException(
                    "La cantidad debe ser mayor que cero");
        }

        articulosAgrupados.merge(
                articulo.getIdmenu(),
                articulo.getCantidad(),
                Integer::sum
        );
    }

    if (articulosAgrupados.isEmpty()) {
        throw new RuntimeException("Ordena algo pueh...");
    }

    BigDecimal total = BigDecimal.ZERO;

    for (Map.Entry<Integer, Integer> entrada
            : articulosAgrupados.entrySet()) {

        Menu menu = menuRepo.findById(entrada.getKey())
                .orElseThrow(() -> new RuntimeException(
                        "Ya no tenemos más existencias...: " + entrada.getKey()));

        if (menu.getActivo() != 1) {
            throw new RuntimeException(
                    "El producto no está disponible ahora...: " + menu.getNombre());
        }

        total = total.add(
                menu.getPrecio().multiply(
                        BigDecimal.valueOf(entrada.getValue())
                )
        );
    }

    Pedido pedido = new Pedido();

    pedido.setIdpedido(pedidoRepo.obtenerUltimoId() + 1);
    pedido.setCliente(cliente);
    pedido.setTotalPrecio(total);
    pedido.setEstado("PENDIENTE");

    pedido = pedidoRepo.save(pedido);

    for (Map.Entry<Integer, Integer> entrada
            : articulosAgrupados.entrySet()) {

        Menu menu = menuRepo.findById(entrada.getKey())
                .orElseThrow(() -> new RuntimeException(
                        "Ya no tenemos más existencias...: " + entrada.getKey()));

        Detalles_de_Pedidos detalle = new Detalles_de_Pedidos();

        detalle.setPedido(pedido);
        detalle.setMenu(menu);
        detalle.setCantidad(entrada.getValue());
        detalle.setPrecioUnitario(menu.getPrecio());

        detallePedidoRepo.save(detalle);
        }
    return pedido;
}
}