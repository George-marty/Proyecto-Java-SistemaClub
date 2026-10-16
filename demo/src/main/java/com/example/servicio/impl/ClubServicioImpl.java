package com.example.servicio.impl;

import java.util.ArrayList;
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
    


    
}
