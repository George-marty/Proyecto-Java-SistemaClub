package com.example;


import java.util.ArrayList;

import javax.swing.SwingUtilities;

import com.example.gui.LoginFrame;
import com.example.modelo.Actividad;
import com.example.modelo.Club;
import com.example.modelo.Inscripcion;
import com.example.modelo.Socio;
import com.example.servicio.SocioServicio;
import com.example.servicio.impl.SocioServicioImpl;


public class Main {
    public static final ArrayList<Club> listaClub = new ArrayList<>();
    public static final ArrayList<Actividad> listaActividad = new ArrayList<>();
    public static final ArrayList<Inscripcion> listaInscripcion = new ArrayList<>();
    public static final ArrayList<Socio> listaSocio = new ArrayList<>();
    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(()-> {
            LoginFrame ventana = new LoginFrame();
            ventana.setVisible(true);
        }
    );*/

    SocioServicio socioServicio = new SocioServicioImpl();
    socioServicio.registrarSocio();
    socioServicio.mostrarSocio(listaSocio);
        
        
    }
}