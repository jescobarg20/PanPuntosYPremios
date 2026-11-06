/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.pan.api.controladores;

import com.mycompany.pan.api.modelos.Pedido;
import com.mycompany.pan.api.servicios.PedidoServicios;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class pedidoControlador {

    private final PedidoServicios pedidoServicio;

    public pedidoControlador(PedidoServicios pedidoServicio) {
        this.pedidoServicio = pedidoServicio;
    }

    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidoServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Pedido> obtenerPorId(@PathVariable int id) {
        return pedidoServicio.obtenerPorId(id);
    }

    @PostMapping
    public Pedido guardar(@RequestBody Pedido pedido) {
        return pedidoServicio.guardar(pedido);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        pedidoServicio.eliminar(id);
    }
}