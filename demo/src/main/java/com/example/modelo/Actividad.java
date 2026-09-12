package com.example.modelo;
/*
Actividad con al menos los siguientes atributos: nombre (String), cupo (int), disponible (boolean),
fechaInicio (LocalDate) */

import java.time.LocalDate;

public class Actividad {
    private int idActividad;
    private String nombre;
    private int cupo;
    private boolean disponible;
    private LocalDate fechaInicio;
    public Actividad() {
    }
    public Actividad(String nombre, int cupo, boolean disponible, LocalDate fechaInicio) {
        this.nombre = nombre;
        this.cupo = cupo;
        this.disponible = disponible;
        this.fechaInicio = fechaInicio;
    }
    public Actividad(int idActividad, String nombre, int cupo, boolean disponible, LocalDate fechaInicio) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.cupo = cupo;
        this.disponible = disponible;
        this.fechaInicio = fechaInicio;
    }
    public int getIdActividad() {
        return idActividad;
    }
    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCupo() {
        return cupo;
    }
    public void setCupo(int cupo) {
        this.cupo = cupo;
    }
    public boolean isDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    @Override
    public String toString() {
        return "Actividad [idActividad=" + idActividad + ", nombre=" + nombre + ", cupo=" + cupo + ", disponible="
                + disponible + ", fechaInicio=" + fechaInicio + "]";
    }

    

}
