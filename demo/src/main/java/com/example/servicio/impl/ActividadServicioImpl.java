package com.example.servicio.impl;


import java.util.ArrayList;

import java.util.regex.Pattern;

import com.example.DAO.ActividadDAO;
import com.example.modelo.Actividad;
import com.example.servicio.ActividadServicio;

public class ActividadServicioImpl implements ActividadServicio {
    @Override 
    public Actividad registrarActividad(Actividad actividad, ArrayList<Actividad> listaActividad){
        
        listaActividad.add(actividad);
        return actividad;
    }
    @Override 
    public void guardarActividad(Actividad actividad, ArrayList<Actividad>listaActividad){
        int id = new ActividadDAO().insertActividad(actividad);
        actividad.setIdActividad(id);
        listaActividad.add(actividad);
    }
    @Override 
    public void validarActividad(String nombre, String cupo) {
    if (nombre == null || !Pattern.matches("[\\p{L}\\d ]{2,50}", nombre.trim())) {
        throw new IllegalArgumentException(
            "El nombre de la actividad debe tener entre 2 y 50 caracteres (letras, números y espacios)");
    }
    if (cupo == null || !Pattern.matches("\\d{1,4}", cupo.trim())) {
        throw new IllegalArgumentException(
            "El cupo debe ser un número entero positivo (hasta 4 dígitos)");
    }
}

}
