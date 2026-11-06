/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.api.controladores;

import com.mycompany.pan.api.modelos.Inventario;
import com.mycompany.pan.api.servicios.InventarioServicios;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author santi
 */

@RestController
@RequestMapping("/api/inventario")
public class inventarioControlador {

    private final InventarioServicios inventarioServicio;

    public inventarioControlador(InventarioServicios inventarioServicio) {
        this.inventarioServicio = inventarioServicio;
    }

    @GetMapping
    public List<Inventario> obtenerTodos() {
        return inventarioServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Inventario> obtenerPorId(@PathVariable int id) {
        return inventarioServicio.obtenerPorId(id);
    }

    @PostMapping
    public Inventario guardar(@RequestBody Inventario inventario) {
        return inventarioServicio.guardar(inventario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        inventarioServicio.eliminar(id);
    }
}