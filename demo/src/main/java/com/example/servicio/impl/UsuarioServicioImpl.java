package com.example.servicio.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;


import com.example.DAO.UsuarioDAO;
import com.example.modelo.Usuario;
import com.example.servicio.UsuarioServicio;

public class UsuarioServicioImpl implements UsuarioServicio{
    
    

    
    public boolean validarUsuario(String username,String passwordHash){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.leerUsuario(username);

        if (usuario.getId() != 0 && usuario.getUsername() != null && usuario.getPasswordHash() != null){
            String claveMD5 = convertirMD5(passwordHash);
            return usuario.getPasswordHash().equalsIgnoreCase(claveMD5);
        }
        return false;
    }

    private String convertirMD5(String input){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Error al encriptar en MD5", e);
        }
    }
}
