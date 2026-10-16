package com.example.modelo;

public class Inscripcion{
    private int idInscripcion;
    private Club club;
    private Actividad Actividad;
    private int precios;
    public Inscripcion() {
    }
    public Inscripcion(Club club, com.example.modelo.Actividad actividad, int precios) {
        this.club = club;
        this.Actividad = actividad;
        this.precios = precios;
    }
    public Inscripcion(int idInscripcion, Club club, com.example.modelo.Actividad actividad, int precios) {
        this.idInscripcion = idInscripcion;
        this.club = club;
        Actividad = actividad;
        this.precios = precios;
    }
    public int getIdInscripcion() {
        return idInscripcion;
    }
    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }
    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }
    public Actividad getActividad() {
        return Actividad;
    }
    public void setActividad(Actividad actividad) {
        Actividad = actividad;
    }
    public int getPrecios() {
        return precios;
    }
    public void setPrecios(int precios) {
        this.precios = precios;
    }
    @Override
    public String toString() {
        return "Inscripcion [idInscripcion=" + idInscripcion + ", club=" + club + ", Actividad=" + Actividad
                + ", precios=" + precios + "]";
    }

    

    
}
