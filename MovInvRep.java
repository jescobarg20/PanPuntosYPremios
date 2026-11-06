/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pan.api.repositorios;

import com.mycompany.pan.api.modelos.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.pan.api.modelos.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


/**
 *
 * @author santi
 */

public interface MovInvRep
        extends JpaRepository<MovimientoInventario, Integer> {
    List<MovimientoInventario> findByTipoInOrderByFechaDesc(List<String> tipos);

}