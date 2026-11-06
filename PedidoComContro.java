package com.mycompany.pan.api.controladores;

import com.mycompany.pan.api.modelos.PedidoCompletoDTO;
import com.mycompany.pan.api.modelos.Pedido;
import com.mycompany.pan.api.servicios.Lista_pedidoSer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos-completos")
public class PedidoComContro {

    private final Lista_pedidoSer listaPedidoSer;

    public PedidoComContro(Lista_pedidoSer listaPedidoSer) {
        this.listaPedidoSer = listaPedidoSer;
    }

    @PostMapping
    public ResponseEntity<?> crearPedido(
            @RequestBody PedidoCompletoDTO datos) {

        try {
            Pedido pedido = listaPedidoSer.crearPedido(datos);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(pedido);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}