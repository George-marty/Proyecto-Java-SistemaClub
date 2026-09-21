package com.example.modelo;

import java.time.LocalDate;

public class Socio {
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private LocalDate fechaAlta;
    private Club club;

    public Socio() {
    }

   

    public Socio(String nombre, String apellido, String dni, String email, LocalDate fechaAlta, Club club) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fechaAlta = fechaAlta;
        this.club = club;
    }

    

    public Socio(int id, String nombre, String apellido, String dni, String email, LocalDate fechaAlta, Club club) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fechaAlta = fechaAlta;
        this.club = club;
    }



    @Override
    public String toString() {
        return "Socio [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", email=" + email
                + ", fecha_alta=" + fechaAlta + ", club=" + club + "]";
    }

    

}
