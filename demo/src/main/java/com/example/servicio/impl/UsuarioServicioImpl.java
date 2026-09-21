package com.example.servicio.impl;

import com.example.DAO.UsuarioDAO;
import com.example.modelo.Usuario;
import com.example.servicio.UsuarioServicio;

public class UsuarioServicioImpl implements UsuarioServicio{
    public boolean validarUsuario(String username,String passwordHash){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.leerUsuario(username);

        if (usuario.getId() != 0 && usuario.getUsername() != null && usuario.getPasswordHash() != null){
            return usuario.getPasswordHash().equals(passwordHash);
        }
        return false;
    }
}
