
package com.mycompany.pan.api.vistas;

import com.mycompany.pan.api.modelos.Menu;
import com.mycompany.pan.api.servicios.MenuServicios;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuWEB {

    private final MenuServicios menuServicios;

    public MenuWEB(MenuServicios menuServicios) {
        this.menuServicios = menuServicios;
    }

    @GetMapping("/productos")
    public String listarProductos(Model model) {

        model.addAttribute("productos",
                menuServicios.obtenerTodos());

        return "productos";
    }
    
    
    @PostMapping("/productos/guardar")
    public String guardarProducto(Menu menu) {

        menuServicios.guardar(menu);

        return "redirect:/productos";
    }
    
    @GetMapping("/productos/editar/{id}")
    public String mostrarEdicion(@PathVariable int id, Model model) {

        Menu producto = menuServicios.obtenerPorId(id).orElse(null);

        if (producto == null) {
            return "redirect:/productos";
        }

        model.addAttribute("producto", producto);

        return "editar-producto";
    }
    
    @PostMapping("/productos/actualizar")
    public String actualizarProducto(Menu menu) {
    
        menuServicios.guardar(menu);
    
        return "redirect:/productos";
    }
    
    @GetMapping("/productos/desactivar/{id}")
    public String desactivarProducto(@PathVariable int id) {

        Menu producto = menuServicios.obtenerPorId(id).orElse(null);

        if (producto != null) {
            producto.setActivo(0);
            menuServicios.guardar(producto);
        }

        return "redirect:/productos";
    }

}
