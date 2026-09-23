package com.example.DAO;

import com.example.control.ConectarBase;
import com.example.modelo.Usuario;

public class UsuarioDAO {
    public int insertarUsuario(Usuario usuario){
        
        int id = 0;
        String sql = "INSERT INTO usuario (username,password_hash) VALUES (?,?)";
        try (var conexion = new ConectarBase().conectar();
            var preparedStatement = conexion.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, usuario.getUsername());
                preparedStatement.setString(2, usuario.getPasswordHash());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0){
                    try (var generatedKeys = preparedStatement.getGeneratedKeys()){
                        if (generatedKeys.next()){
                            id = generatedKeys.getInt(1);
                        }
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        return id;
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
