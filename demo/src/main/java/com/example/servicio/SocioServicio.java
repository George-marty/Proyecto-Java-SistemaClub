package com.example.servicio;

import java.util.ArrayList;

import com.example.modelo.Socio;

public interface SocioServicio {
    public Socio registrarSocio();
    public void guardarSocio(Socio socio, ArrayList<Socio> listaSocio);
    public void mostrarSocio(ArrayList<Socio> listaSocio);
}
