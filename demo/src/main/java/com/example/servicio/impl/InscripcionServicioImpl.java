package com.example.servicio.impl;


import java.util.ArrayList;


import com.example.DAO.InscripcionDAO;

import com.example.modelo.Inscripcion;

import com.example.servicio.InscripcionServicio;

public class InscripcionServicioImpl implements InscripcionServicio {
    @Override 
    public Inscripcion registrarInscripcion(Inscripcion inscripcion,ArrayList<Inscripcion>listaInscripcion){
        listaInscripcion.add(inscripcion);
        return  inscripcion;
        
    }
    @Override 
    public void guardarInscripcion(Inscripcion inscripcion,ArrayList<Inscripcion>listaInscripcion){
        int idInscripcion = new InscripcionDAO().insertarInscripcion(inscripcion);
        inscripcion.setIdInscripcion(idInscripcion);
        listaInscripcion.add(inscripcion);
    }
    @Override 
    public void mostrarInscripcion(ArrayList<Inscripcion>listaInscripcion){
        for (Inscripcion inscripcion : listaInscripcion) {
            System.out.println(inscripcion.toString());
        }
    }
}
