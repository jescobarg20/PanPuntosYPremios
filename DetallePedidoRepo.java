/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pan.api.repositorios;

import com.mycompany.pan.api.modelos.Detalles_de_Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.mycompany.pan.api.modelos.Detalles_de_Pedidos;

@Repository
public interface DetallePedidoRepo
        extends JpaRepository<Detalles_de_Pedidos, Integer> {
    List<Detalles_de_Pedidos> findByPedido_Idpedido(int idpedido);
    
}