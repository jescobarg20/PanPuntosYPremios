/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.api.controladores;

import com.mycompany.pan.api.modelos.Menu;
import com.mycompany.pan.api.servicios.MenuServicios;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
public class menuControlador {

    private final MenuServicios menuServicio;

    public menuControlador(MenuServicios menuServicio) {
        this.menuServicio = menuServicio;
    }


    @GetMapping
    public List<Menu> obtenerTodos() {
        return menuServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Menu> obtenerPorId(@PathVariable int id) {
        return menuServicio.obtenerPorId(id);
    }

    @PostMapping
    public Menu guardar(@RequestBody Menu menu) {
        return menuServicio.guardar(menu);
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        menuServicio.eliminar(id);
    }
}
