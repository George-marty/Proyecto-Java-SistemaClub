package com.example.DAO;

import java.util.ArrayList;

import com.example.control.ConectarBase;
import com.example.modelo.Actividad;

public class ActividadDAO {
    public int insertActividad(Actividad actividad) {
        int id = 0;
        String sql = "INSERT INTO actividad (nombre,cupo,disponible,fecha_inicio,descripcion) VALUES (?,?,?,?,?)";
        try (var conexion = new ConectarBase().conectar();
                var preparedStatement = conexion.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, actividad.getNombre());
            preparedStatement.setInt(2, actividad.getCupo());
            preparedStatement.setBoolean(3, actividad.isDisponible());
            preparedStatement.setDate(4, java.sql.Date.valueOf(actividad.getFechaInicio()));
            preparedStatement.setString(5, actividad.getDescripcion());

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
        String sql = "UPDATE actividad set nombre = ?, cupo = ?, disponible = ?, fecha_inicio = ?, descripcion = ? WHERE id = ? ";
        try (var conexion = new ConectarBase().conectar();
        var preparedStatement = conexion.prepareStatement(sql)){
            preparedStatement.setString(1, actividad.getNombre());
            preparedStatement.setInt(2, actividad.getCupo());
            preparedStatement.setBoolean(3, actividad.isDisponible());
            preparedStatement.setDate(4, java.sql.Date.valueOf(actividad.getFechaInicio()));
            preparedStatement.setString(5, actividad.getDescripcion());
            preparedStatement.setInt(6, actividad.getId());
            int affectedRows = preparedStatement.executeUpdate();
            return  affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    public static boolean borrarActividad(int id){
        String sql = "DELETE FROM actividad WHERE id = ?";
        try (var conexion = new ConectarBase().conectar();
        var preparedStatement = conexion.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return  affectedRows > 0;
        } catch (Exception e){
            e.printStackTrace();
            return false;
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
                actividad.setId(resultSet.getInt("id"));
                actividad.setNombre(resultSet.getString("nombre"));
                actividad.setCupo(resultSet.getInt("cupo"));
                actividad.setDisponible(resultSet.getBoolean("disponible"));
                actividad.setFechaInicio(resultSet.getDate("fecha_inicio").toLocalDate());
                actividad.setDescripcion(resultSet.getString("descripcion"));
                listaActividad.add(actividad);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listaActividad;
    }

}
