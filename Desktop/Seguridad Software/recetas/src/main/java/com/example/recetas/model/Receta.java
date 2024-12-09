package com.example.recetas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int anioCreacion;
    private String vigente;

    // GET
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnioCreacion() {
        return anioCreacion;
    }

    public String getVigente() {
        return vigente;
    }

    // SET
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAnioCreacion(int anioCreacion) {
        this.anioCreacion = anioCreacion;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }
}
