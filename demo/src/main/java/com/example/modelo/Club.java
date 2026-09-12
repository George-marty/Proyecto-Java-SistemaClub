package com.example.modelo;
/*/
 Club con al menos los siguientes atributos: nombre (String), socios (int),
activo (boolean), fechaFundacion (LocalDate)
*/

import java.time.LocalDate;

public class Club {
    private int idClub;
    private String nombre;
    private int socios;
    private boolean activo;
    private LocalDate fechaFundacion;

    public Club() {
    }

    public Club(String nombre, int socios, boolean activo, LocalDate fechaFundacion) {
        this.nombre = nombre;
        this.socios = socios;
        this.activo = activo;
        this.fechaFundacion = fechaFundacion;
    }

    public Club(int idClub, String nombre, int socios, boolean activo, LocalDate fechaFundacion) {
        this.idClub = idClub;
        this.nombre = nombre;
        this.socios = socios;
        this.activo = activo;
        this.fechaFundacion = fechaFundacion;
    }

    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSocios() {
        return socios;
    }

    public void setSocios(int socios) {
        this.socios = socios;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(LocalDate fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    @Override
    public String toString() {
        return "Club [idClub=" + idClub + ", nombre=" + nombre + ", socios=" + socios + ", activo=" + activo
                + ", fechaFundacion=" + fechaFundacion + "]";
    }

    

    
    

}
