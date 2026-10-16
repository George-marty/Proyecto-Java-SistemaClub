package com.example.DAO;

import java.util.ArrayList;

import com.example.control.ConectarBase;
import com.example.modelo.Actividad;
import com.example.modelo.Club;
import com.example.modelo.Inscripcion;

public class InscripcionDAO {
    public int insertarInscripcion(Inscripcion inscripcion) {
        int idInscripcion = 0;
        
        String sql = "INSERT INTO inscripcion (Club_idClub,Actividad_idActividad,precios) VALUES (?,?,?)";
        try (var conexion = new ConectarBase().conectar();
                var preparedStatement = conexion.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, inscripcion.getClub().getIdClub());
            preparedStatement.setInt(2, inscripcion.getActividad().getIdActividad());
            preparedStatement.setInt(3, inscripcion.getPrecios());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idInscripcion = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idInscripcion;
    }

   public boolean modificarInscripcion(Inscripcion inscripcion) {
        boolean modificado = false;
        String sql = "UPDATE inscripcion SET Club_idClub = ?, Actividad_idActividad = ?, precios = ? WHERE idInscripcion = ?";
        
        try (var conexion = new ConectarBase().conectar();
             var preparedStatement = conexion.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, inscripcion.getClub().getIdClub());
            preparedStatement.setInt(2, inscripcion.getActividad().getIdActividad());
            preparedStatement.setInt(3, inscripcion.getPrecios());
            preparedStatement.setInt(4, inscripcion.getIdInscripcion());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                modificado = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modificado;
    }

    public boolean borrarInscripcion(int idInscripcion) {
        boolean borrado = false;
        String sql = "DELETE FROM inscripcion WHERE idInscripcion = ?";
        
        try (var conexion = new ConectarBase().conectar();
             var preparedStatement = conexion.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, idInscripcion);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                borrado = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return borrado;
    }

    public ArrayList<Inscripcion> obtenerInscripcion() {
        ArrayList<Inscripcion> listaInscripcion = new ArrayList<>();
        String sql = "SELECT i.idInscripcion,c.nombre AS nombre_club, c.activo,\r\n" + //
                        "       a.nombre AS nombre_actividad, a.disponible,\r\n" + //
                        "       i.precios AS precio_inscripcion\r\n" + //
                        "FROM `Inscripcion` AS i\r\n" + //
                        "INNER JOIN `Club` AS c ON c.idClub = i.Club_idClub\r\n" + //
                        "INNER JOIN `Actividad` AS a ON a.idActividad = i.Actividad_idActividad;";

        try (var conexion = new ConectarBase().conectar();
                var statement = conexion.createStatement();
                var resultSet = statement.executeQuery(sql)) {
            
                while (resultSet.next()) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(resultSet.getInt("idInscripcion"));
                Club club = new Club();
                club.setNombre(resultSet.getString("nombre_club"));
                club.setActivo(resultSet.getBoolean("activo"));
                inscripcion.setClub(club);
                Actividad actividad = new Actividad();
                actividad.setNombre(resultSet.getString("nombre_actividad"));
                actividad.setDisponible(resultSet.getBoolean("disponible"));
                inscripcion.setActividad(actividad);
                inscripcion.setPrecios(resultSet.getInt("precio_inscripcion"));
                listaInscripcion.add(inscripcion);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return  listaInscripcion;
    }
}
