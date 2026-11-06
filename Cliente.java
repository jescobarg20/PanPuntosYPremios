
package com.mycompany.pan.api.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @Column(name = "idClientes")
    private int idClientes;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "apellidos", length = 45)
    private String apellidos;

    @Column(name = "puntos")
    private int puntos;
    
    @Column(name = "activo")
    private int activo = 1;

    // Constructor vacío
    public Cliente() {
    }

    // Getters y Setters

    public int getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(int idClientes) {
        this.idClientes = idClientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
