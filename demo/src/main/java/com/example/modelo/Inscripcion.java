package com.example.modelo;

import java.time.LocalDate;

public class Inscripcion {
    private int id;
    private Socio socio;
    private Actividad actividad;
    private LocalDate fechaInscripcion;
    
    public Inscripcion() {
    }
    
    public Inscripcion(Socio socio, Actividad actividad, LocalDate fechaInscripcion) {
        this.socio = socio;
        this.actividad = actividad;
        this.fechaInscripcion = fechaInscripcion;
    }
    
    public Inscripcion(int id, Socio socio, Actividad actividad, LocalDate fechaInscripcion) {
        this.id = id;
        this.socio = socio;
        this.actividad = actividad;
        this.fechaInscripcion = fechaInscripcion;
    }

    @Override
    public String toString() {
        return "Inscripcion [id=" + id + ", socio=" + socio + ", actividad=" + actividad + ", fecha_inscripcion="
                + fechaInscripcion + "]";
    }

    

}
