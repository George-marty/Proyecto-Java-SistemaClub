package com.example.DAO;

import java.util.ArrayList;

import com.example.control.ConectarBase;
import com.example.modelo.Club;

public class ClubDAO {
    public int insertClub(Club club) {
        int id = 0;
        String sql = "INSERT INTO club (nombre,socios,activo,fechaFundacion) VALUES (?,?,?,?)";
        try (var conexion = new ConectarBase().conectar();
                var preparedStatement = conexion.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, club.getNombre());
            preparedStatement.setInt(2, club.getSocios());
            preparedStatement.setBoolean(3, club.isActivo());
            preparedStatement.setDate(4, java.sql.Date.valueOf(club.getFechaFundacion()));

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

    public boolean modificarClub(Club club) {
        String sql = "UPDATE club set nombre = ?,socios = ?, activo = ?, fechaFundacion = ? WHERE idClub = ?";
        try (var conexion = new ConectarBase().conectar();
                var preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, club.getNombre());
            preparedStatement.setInt(2, club.getSocios());
            preparedStatement.setBoolean(3, club.isActivo());
            preparedStatement.setDate(4, java.sql.Date.valueOf(club.getFechaFundacion()));
            preparedStatement.setInt(5, club.getIdClub());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean borrarClub(int id) {
        String sql = "DELETE FROM club WHERE idClub = ?";
        try (var conexion = new ConectarBase().conectar();
                var preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Club> obtenerClub() {
        ArrayList<Club> listaClub = new ArrayList<>();
        String sql = "SELECT * FROM club";

        try (var conexion = new ConectarBase().conectar();
                var statement = conexion.createStatement();
                var resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Club club = new Club();
                club.setIdClub(resultSet.getInt("idClub"));
                club.setNombre(resultSet.getString("nombre"));
                club.setSocios(resultSet.getInt("socios"));
                club.setActivo(resultSet.getBoolean("activo"));
                club.setFechaFundacion(resultSet.getDate("fechaFundacion").toLocalDate());
                
                listaClub.add(club);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaClub;
    }

}
