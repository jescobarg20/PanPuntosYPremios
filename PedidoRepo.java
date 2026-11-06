/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pan.api.repositorios;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author santi
 */

import com.mycompany.pan.api.modelos.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepo extends JpaRepository<Pedido, Integer> {
    
    @Query("SELECT COALESCE(MAX(p.idpedido), 0) FROM Pedido p")
    int obtenerUltimoId();

}
