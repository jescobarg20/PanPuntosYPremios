
package com.mycompany.pan.api.servicios;

import com.mycompany.pan.api.modelos.Cliente;
import com.mycompany.pan.api.repositorios.ClienteRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {

    private final ClienteRepo clienteRepo;

    public ClienteServicio(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepo.findAll();
    }

    public Optional<Cliente> obtenerPorId(int id) {
        return clienteRepo.findById(id);
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    public void eliminar(int id) {

        Optional<Cliente> resultado = clienteRepo.findById(id);

        if (resultado.isPresent()) {
            Cliente cliente = resultado.get();

            cliente.setActivo(0);

            clienteRepo.save(cliente);
        }
    }
}
