package com.example.modelo;
/*/
 Club con al menos los siguientes atributos: nombre (String), socios (int),
activo (boolean), fechaFundacion (LocalDate)
*/

import java.time.LocalDate;

public class Club {
    private int id;
    private String nombre;
    private int socios;
    private boolean activo;
    private LocalDate fechaFundacion;
    private String direccion;
    private String email_contacto;

    public Club() {
    }

    public Club(String nombre, int socios, boolean activo, LocalDate fechaFundacion, String direccion,
            String email_contacto) {
        this.nombre = nombre;
        this.socios = socios;
        this.activo = activo;
        this.fechaFundacion = fechaFundacion;
        this.direccion = direccion;
        this.email_contacto = email_contacto;
    }

    public Club(int id, String nombre, int socios, boolean activo, LocalDate fechaFundacion, String direccion,
            String email_contacto) {
        this.id = id;
        this.nombre = nombre;
        this.socios = socios;
        this.activo = activo;
        this.fechaFundacion = fechaFundacion;
        this.direccion = direccion;
        this.email_contacto = email_contacto;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail_contacto() {
        return email_contacto;
    }

    public void setEmail_contacto(String email_contacto) {
        this.email_contacto = email_contacto;
    }

    @Override
    public String toString() {
        return "Club [id=" + id + ", nombre=" + nombre + ", socios=" + socios + ", activo=" + activo
                + ", fechaFundacion=" + fechaFundacion + ", direccion=" + direccion + ", email_contacto="
                + email_contacto + "]";
    }

    

    

    
    

}
