
package com.mycompany.pan.api.vistas;

import com.mycompany.pan.api.modelos.Cliente;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mycompany.pan.api.servicios.ClienteServicio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientesWEB {

    private final ClienteServicio clienteServicio;

    public ClientesWEB(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @GetMapping("/clientes")
    public String listarClientes(Model model) {

        model.addAttribute("clientes",
                clienteServicio.obtenerTodos());

        return "clientes";
    }
    
    
    @GetMapping("/clientes/buscar")
    public String buscarCliente(
            @org.springframework.web.bind.annotation.RequestParam int id,
            Model model) {

        Cliente cliente = clienteServicio.obtenerPorId(id).orElse(null);

        java.util.List<Cliente> resultados = new java.util.ArrayList<>();

        if (cliente != null) {
            resultados.add(cliente);
        }

        model.addAttribute("clientes", resultados);
        model.addAttribute("busqueda", id);

        return "clientes";
    }

    
    
    @PostMapping("/clientes/guardar")
    public String guardarCliente(Cliente cliente) {

        clienteServicio.guardar(cliente);

        return "redirect:/clientes";
    }

    
    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable int id) {

        clienteServicio.eliminar(id);

        return "redirect:/clientes";
    }
    
    
    @GetMapping("/clientes/editar/{id}")
    public String mostrarEdicion(@PathVariable int id, Model model) {

        Cliente cliente = clienteServicio.obtenerPorId(id).orElse(null);

        if (cliente == null) {
            return "redirect:/clientes";
        }

        model.addAttribute("cliente", cliente);

        return "editar-cliente";
    }
    
    
    @PostMapping("/clientes/actualizar")
    public String actualizarCliente(Cliente cliente) {

        clienteServicio.guardar(cliente);

        return "redirect:/clientes";
    }



    
}
