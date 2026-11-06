/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.api.modelos;

/**
 *
 * @author santi
 */ 

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @Column(name = "idpedido")
    private int idpedido;

    @Column(name = "TotalPrecio")
    private BigDecimal TotalPrecio;

    @Column(name = "Estado", length = 45)
    private String Estado;

    @ManyToOne
    @JoinColumn(name = "idClientes")
    private Cliente cliente;

    public Pedido() {
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public BigDecimal getTotalPrecio() {
        return TotalPrecio;
    }

    public void setTotalPrecio(BigDecimal TotalPrecio) {
        this.TotalPrecio = TotalPrecio;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
