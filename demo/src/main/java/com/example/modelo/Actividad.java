package com.example.modelo;
/*
Actividad con al menos los siguientes atributos: nombre (String), cupo (int), disponible (boolean),
fechaInicio (LocalDate) */

import java.time.LocalDate;

public class Actividad {
    private int id;
    private String nombre;
    private int cupo;
    private boolean disponible;
    private LocalDate fechaInicio;
    private Club club;
    public Actividad() {
    }
    public Actividad(String nombre, int cupo, boolean disponible, LocalDate fechaInicio, Club club) {
        this.nombre = nombre;
        this.cupo = cupo;
        this.disponible = disponible;
        this.fechaInicio = fechaInicio;
        this.club = club;
    }
    public Actividad(int id, String nombre, int cupo, boolean disponible, LocalDate fechaInicio, Club club) {
        this.id = id;
        this.nombre = nombre;
        this.cupo = cupo;
        this.disponible = disponible;
        this.fechaInicio = fechaInicio;
        this.club = club;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }
    @Override
    public String toString() {
        return "Actividad [id=" + id + ", nombre=" + nombre + ", cupo=" + cupo + ", disponible="
                + disponible + ", fechaInicio=" + fechaInicio + ", club=" + club + "]";
    }

    


    

}
