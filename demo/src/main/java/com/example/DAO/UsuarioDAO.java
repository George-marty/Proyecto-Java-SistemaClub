package com.example.DAO;

import com.example.control.ConectarBase;
import com.example.modelo.Usuario;

public class UsuarioDAO {
    public Usuario registrarUsuario(){
        Usuario usuario = new Usuario();
        return usuario;
    }
    
    
    
    public Usuario leerUsuario(String username){
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuario WHERE username = ?";
        try {
            var conexion = new ConectarBase().conectar();
            var preparedStatement = conexion.prepareStatement(sql);
            
            preparedStatement.setString(1,username);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                String passwordHash = resultSet.getString("password_hash");
                usuario.setId(id);
                usuario.setUsername(username);
                usuario.setPasswordHash(passwordHash);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
