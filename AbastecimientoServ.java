/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.api.servicios;

import com.mycompany.pan.api.modelos.AbastecimientoDTO;
import com.mycompany.pan.api.modelos.Inventario;
import com.mycompany.pan.api.modelos.Menu;
import com.mycompany.pan.api.modelos.MovimientoInventario;
import com.mycompany.pan.api.repositorios.InventarioRepo;
import com.mycompany.pan.api.repositorios.MenuRepo;
import com.mycompany.pan.api.repositorios.MovInvRep;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import com.mycompany.pan.api.modelos.ElAjusteInvDTO;
/**
 *
 * @author santi
 */


@Service
public class AbastecimientoServ {

    private final InventarioRepo inventarioRepo;
    private final MenuRepo menuRepo;
    private final MovInvRep movInvRep;

    public AbastecimientoServ(
            InventarioRepo inventarioRepo,
            MenuRepo menuRepo,
            MovInvRep movInvRep) {
        this.inventarioRepo = inventarioRepo;
        this.menuRepo = menuRepo;
        this.movInvRep = movInvRep;
    }

    
@Transactional
public MovimientoInventario registrarAjuste(ElAjusteInvDTO dto) {

    if (dto.getCantidad() == 0) {
        throw new IllegalArgumentException(
                "El ajuste no puede ser cero.");
    }

    Menu menu = menuRepo.findById(dto.getIdmenu())
            .orElseThrow(() -> new RuntimeException(
                    "No existe el artículo indicado."));

    Inventario inventario = inventarioRepo.findAll()
            .stream()
            .filter(i -> i.getMenu().getIdmenu() == dto.getIdmenu())
            .findFirst()
            .orElseThrow(() -> new RuntimeException(
                    "No existe inventario para este artículo."));

    int nuevasUnidades = inventario.getUnidades() + dto.getCantidad();

    if (nuevasUnidades < 0) {
        throw new IllegalArgumentException(
                "El ajuste no puede dejar el inventario negativo.");
    }

    inventario.setUnidades(nuevasUnidades);
    inventarioRepo.save(inventario);

    MovimientoInventario movimiento = new MovimientoInventario();
    movimiento.setMenu(menu);
    movimiento.setCantidad(dto.getCantidad());
    movimiento.setTipo("AJUSTE");
    movimiento.setFecha(LocalDateTime.now());

    return movInvRep.save(movimiento);
}

}