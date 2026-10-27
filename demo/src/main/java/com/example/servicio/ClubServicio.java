package com.example.servicio;

import java.util.ArrayList;

import com.example.modelo.Club;

public interface ClubServicio {
    public Club registrarClub(Club club, ArrayList<Club> listaClub);
    public void guardarClub(Club club, ArrayList<Club> listaClub);
    public void mostrarClub(ArrayList<Club> listaClub);
    public void validarClub(String nombre, String socios);
    
}
