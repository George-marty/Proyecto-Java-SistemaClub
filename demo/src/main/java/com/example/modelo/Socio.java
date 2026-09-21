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



    public String getApellido() {
        return apellido;
    }



    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    public String getDni() {
        return dni;
    }



    public void setDni(String dni) {
        this.dni = dni;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public LocalDate getFechaAlta() {
        return fechaAlta;
    }



    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }



    public Club getClub() {
        return club;
    }



    public void setClub(Club club) {
        this.club = club;
    }



    @Override
    public String toString() {
        return "Socio [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", email=" + email
                + ", fecha_alta=" + fechaAlta + ", club=" + club + "]";
    }

    

}
