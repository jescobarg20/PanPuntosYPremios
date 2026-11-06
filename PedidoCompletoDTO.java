/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.api.modelos;

import java.util.List;

public class PedidoCompletoDTO {

    private int idCliente;
    private List<Creador_de_pedidosDTO> articulos;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public List<Creador_de_pedidosDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Creador_de_pedidosDTO> articulos) {
        this.articulos = articulos;
    }
}