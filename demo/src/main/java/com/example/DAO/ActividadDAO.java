package com.example.DAO;

import java.util.ArrayList;

import com.example.control.ConectarBase;
import com.example.modelo.Actividad;

public class ActividadDAO {
    public int insertActividad(Actividad actividad) {
        int id = 0;
        String sql = "INSERT INTO actividad (nombre,cupo,disponible,fechaInicio) VALUES (?,?,?,?)";
        try (var conexion = new ConectarBase().conectar();
                var preparedStatement = conexion.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, actividad.getNombre());
            preparedStatement.setInt(2, actividad.getCupo());
            preparedStatement.setBoolean(3, actividad.isDisponible());
            preparedStatement.setDate(4, java.sql.Date.valueOf(actividad.getFechaInicio()));
            

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        id = generatedKeys.getInt(1);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean modificarActividad(Actividad actividad){
        String sql = "UPDATE actividad set nombre = ?, cupo = ?, disponible = ?, fechaInicio = ?WHERE idActividad = ? ";
        try (var conexion = new ConectarBase().conectar();
        var preparedStatement = conexion.prepareStatement(sql)){
            preparedStatement.setString(1, actividad.getNombre());
            preparedStatement.setInt(2, actividad.getCupo());
            preparedStatement.setBoolean(3, actividad.isDisponible());
            preparedStatement.setDate(4, java.sql.Date.valueOf(actividad.getFechaInicio()));
            
            preparedStatement.setInt(6, actividad.getIdActividad());
            int affectedRows = preparedStatement.executeUpdate();
            return  affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    public static boolean borrarActividad(int id) throws java.sql.SQLException{
        String sql = "DELETE FROM actividad WHERE idActividad = ?";
        try (var conexion = new ConectarBase().conectar();
        var preparedStatement = conexion.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return  affectedRows > 0;
        } 
    }
    

    public ArrayList<Actividad> obtenerActividad(){
        ArrayList<Actividad> listaActividad = new ArrayList<>();
        String sql = "SELECT * FROM actividad";
        try (var conexion = new ConectarBase().conectar();
        var statement = conexion.createStatement();
        var resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                Actividad actividad = new Actividad();
                actividad.setIdActividad(resultSet.getInt("idActividad"));
                actividad.setNombre(resultSet.getString("nombre"));
                actividad.setCupo(resultSet.getInt("cupo"));
                actividad.setDisponible(resultSet.getBoolean("disponible"));
                actividad.setFechaInicio(resultSet.getDate("fechaInicio").toLocalDate());
                listaActividad.add(actividad);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listaActividad;
    }

}
