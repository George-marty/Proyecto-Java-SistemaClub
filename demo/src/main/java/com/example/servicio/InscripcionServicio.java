package com.example.servicio;

import java.util.ArrayList;

import com.example.modelo.Inscripcion;

public interface InscripcionServicio {
    public Inscripcion registrarInscripcion();
    public void guardarInscripcion(Inscripcion inscripcion,ArrayList<Inscripcion>listaInscripcion);
    public void mostrarInscripcion(ArrayList<Inscripcion>listaInscripcion);
}
