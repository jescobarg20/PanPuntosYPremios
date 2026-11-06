
package com.mycompany.pan.api.controladores;

import com.mycompany.pan.api.modelos.Cliente;
import com.mycompany.pan.api.servicios.ClienteServicio;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class clienteControlador {

    private final ClienteServicio clienteServicio;

    public clienteControlador(ClienteServicio clienteServicio) {
        
        this.clienteServicio = clienteServicio;
    }
    
    
    @GetMapping
    
    public List<Cliente> obtenerTodos() {
        
        return clienteServicio.obtenerTodos();
    }
    
    // por id
    @GetMapping("/{id}")
    
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable int id) {
        
        return clienteServicio.obtenerPorId(id)
                
                .map(ResponseEntity::ok)
                
                .orElse(ResponseEntity.notFound().build());
    }


    
    
    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente) {
        return clienteServicio.guardar(cliente);
    }


    
    @DeleteMapping("/{id}")
    
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        
        clienteServicio.eliminar(id);
        
        return ResponseEntity.noContent().build();
    }
}
