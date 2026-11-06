/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.api.servicios;

/**
 *
 * @author santi
 */
import com.mycompany.pan.api.modelos.Menu;
import com.mycompany.pan.api.repositorios.MenuRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MenuServicios {

    private final MenuRepo menuRepo;

    public MenuServicios(MenuRepo menuRepo) {
        this.menuRepo = menuRepo;
    }

    public List<Menu> obtenerTodos() {
        return menuRepo.findAll();
    }

    public Optional<Menu> obtenerPorId(int id) {
        return menuRepo.findById(id);
    }

    public Menu guardar(Menu menu) {
        return menuRepo.save(menu);
    }

    public void eliminar(int id) {
        menuRepo.deleteById(id);
    }
}
