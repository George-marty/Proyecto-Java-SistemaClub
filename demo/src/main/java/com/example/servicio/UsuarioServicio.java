package com.example.servicio;

import com.example.modelo.Usuario;

public interface UsuarioServicio {
    public boolean validarUsuario(String username, String passwordHash);
    public Usuario registrarUsuario(String username, String password);
}
