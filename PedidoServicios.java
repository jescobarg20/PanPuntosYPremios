/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.api.servicios;

import com.mycompany.pan.api.modelos.Pedido;
import com.mycompany.pan.api.repositorios.PedidoRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PedidoServicios {

    private final PedidoRepo pedidoRepo;

    public PedidoServicios(PedidoRepo pedidoRepo) {
        this.pedidoRepo = pedidoRepo;
    }

    public List<Pedido> obtenerTodos() {
        return pedidoRepo.findAll();
    }

    public Optional<Pedido> obtenerPorId(int id) {
        return pedidoRepo.findById(id);
    }

    public Pedido guardar(Pedido pedido) {
        return pedidoRepo.save(pedido);
    }

    public void eliminar(int id) {
        pedidoRepo.deleteById(id);
    }
}