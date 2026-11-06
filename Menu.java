package com.mycompany.pan.api.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.math.BigDecimal;

@Entity

@Table(name = "menu")

public class Menu {

    @Id
    @Column(name = "idmenu")
    
    private int idmenu;

    @Column(name = "nombre", length = 45)
    
    private String nombre;

    @Column(name = "precio")
    
    private BigDecimal precio;

    @Column(name = "individual")
    
    private int individual;
    
    @Column(name = "activo")
    private int activo;

    public Menu() {
        
    }

    //

    public int getIdmenu() {
        
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        
        this.idmenu = idmenu;
    }

    public String getNombre() {
        
        return nombre;
    }

    public void setNombre(String nombre) {
        
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getIndividual() {
        
        return individual;
    }

    public void setIndividual(int individual) {
        
        this.individual = individual;
    }
    
    public int getActivo() {
    return activo;
}

    public void setActivo(int activo) {
    this.activo = activo;
}
}