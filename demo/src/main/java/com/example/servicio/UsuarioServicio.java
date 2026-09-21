package com.example.servicio;

public interface UsuarioServicio {
    public boolean validarUsuario(String username, String passwordHash);
}
