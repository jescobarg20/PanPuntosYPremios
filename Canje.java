
package com.mycompany.pan.api.modelos;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "canjes")
public class Canje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcanje")
    private int idcanje;

    @ManyToOne
    @JoinColumn(name = "idClientes")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idrecompensa")
    private Recom recompensa;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "puntosUtilizados")
    private int puntosUtilizados;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    public int getIdcanje() {
        return idcanje;
    }

    public void setIdcanje(int idcanje) {
        this.idcanje = idcanje;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Recom getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(Recom recompensa) {
        this.recompensa = recompensa;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPuntosUtilizados() {
        return puntosUtilizados;
    }

    public void setPuntosUtilizados(int puntosUtilizados) {
        this.puntosUtilizados = puntosUtilizados;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
