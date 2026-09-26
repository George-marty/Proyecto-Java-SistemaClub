package com.example.servicio;

import java.util.ArrayList;

import com.example.modelo.Club;

public interface ClubServicio {
    public Club registrarClub();
    public void guardarClub(Club club, ArrayList<Club> listaClub);
    
}
