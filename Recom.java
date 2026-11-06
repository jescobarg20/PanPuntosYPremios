package com.mycompany.pan.api.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "recompensas")
public class Recom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrecompensa")
    private int idrecompensa;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "puntosNecesarios")
    private int puntosNecesarios;

    @Column(name = "activo")
    private int activo;

    @ManyToOne
    @JoinColumn(name = "idmenu")
    private Menu menu;

    public int getIdrecompensa() {
        return idrecompensa;
    }

    public void setIdrecompensa(int idrecompensa) {
        this.idrecompensa = idrecompensa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosNecesarios() {
        return puntosNecesarios;
    }

    public void setPuntosNecesarios(int puntosNecesarios) {
        this.puntosNecesarios = puntosNecesarios;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}