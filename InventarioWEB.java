
package com.mycompany.pan.api.vistas;

import com.mycompany.pan.api.servicios.InventarioServicios;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InventarioWEB {

    private final InventarioServicios inventarioServicios;

    public InventarioWEB(InventarioServicios inventarioServicios) {
        this.inventarioServicios = inventarioServicios;
    }

    @GetMapping("/inventario")
    public String mostrarInventario(Model model) {

        model.addAttribute(
                "inventarios",
                inventarioServicios.obtenerTodos()
        );

        return "inventario";
    }

    @PostMapping("/inventario/registrar")
    public String registrarExistencias(
            @RequestParam int idmenu,
            @RequestParam int unidades,
            RedirectAttributes redirectAttributes) {

        try {

            inventarioServicios.registrarExistencias(idmenu, unidades);

            redirectAttributes.addFlashAttribute(
                    "mensaje",
                    "Inventario actualizado correctamente."
            );

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage()
            );
        }

        return "redirect:/inventario";
    }
}
