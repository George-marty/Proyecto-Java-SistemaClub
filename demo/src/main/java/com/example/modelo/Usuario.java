package com.example.modelo;

import java.time.LocalDateTime;

public class Usuario {
    private int id;
    private String username;
    private String passwordHash;
    private String rol;
    private boolean activo;
    private LocalDateTime fechaCreacion;
    private Socio socio;

    public Usuario() {
    }

    public Usuario(String username, String passwordHash, String rol, boolean activo, LocalDateTime fechaCreacion,
            Socio socio) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.socio = socio;
    }

    public Usuario(int id, String username, String passwordHash, String rol, boolean activo,
            LocalDateTime fechaCreacion, Socio socio) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.socio = socio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", username=" + username + ", rol=" + rol
                + ", activo=" + activo + ", fecha_creacion=" + fechaCreacion + ", socio=" + socio + "]";
    }

}
