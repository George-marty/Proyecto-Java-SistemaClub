package com.example.servicio.impl;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.example.DAO.ClubDAO;
import com.example.modelo.Club;
import com.example.servicio.ClubServicio;

public class ClubServicioImpl implements ClubServicio{
    @Override 
    public Club registrarClub(Club club, ArrayList<Club>listaClub){
        listaClub.add(club);
        return club;
    }

    @Override
    public void mostrarClub(ArrayList<Club>listaClub){
       for (Club club : listaClub) {
            System.out.println(club);
       }

    }

    @Override 
    public void guardarClub(Club club, ArrayList<Club> listaClub){
        int id = new ClubDAO().insertClub(club);
        club.setIdClub(id);
        listaClub.add(club);
        
    }
    @Override 
    public void validarClub(String nombre, String socios) {
    if (nombre == null || !Pattern.matches("[\\p{L}\\d ]{2,50}", nombre.trim())) {
        throw new IllegalArgumentException(
            "El nombre del club debe tener entre 2 y 50 caracteres (letras, números y espacios)");
    }
    if (socios == null || !Pattern.matches("\\d{1,6}", socios.trim())) {
        throw new IllegalArgumentException(
            "Socios debe ser un número entero positivo (hasta 6 dígitos)");
    }
}
    


    
}
