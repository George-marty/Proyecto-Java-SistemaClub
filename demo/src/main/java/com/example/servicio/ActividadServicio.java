package com.example.servicio;

import java.util.ArrayList;

import com.example.modelo.Actividad;

public interface ActividadServicio {
    public Actividad registrarActividad(Actividad actividad, ArrayList<Actividad> listaActividad);
    public void guardarActividad(Actividad actividad, ArrayList<Actividad>listaActividad);
    public void validarActividad(String nombre, String cupo);
}
