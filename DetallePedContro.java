/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.api.controladores;

/**
 *
 * @author santi
 */
import com.mycompany.pan.api.modelos.Detalles_de_Pedidos;
import com.mycompany.pan.api.servicios.DetallesPedServ;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detalles-pedido")
public class DetallePedContro {

    private final DetallesPedServ detallesPedServ;

    public DetallePedContro(
            DetallesPedServ detallesPedServ) {
        this.detallesPedServ = detallesPedServ;
    }

    @GetMapping
    public List<Detalles_de_Pedidos> listarTodos() {
        return detallesPedServ.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detalles_de_Pedidos> buscarPorId(
            @PathVariable int id) {

        Optional<Detalles_de_Pedidos> detalle =
                detallesPedServ.buscarPorId(id);

        return detalle.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}