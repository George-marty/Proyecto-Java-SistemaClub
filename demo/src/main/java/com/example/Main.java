package com.example;

import java.util.ArrayList;
import javax.swing.SwingUtilities;

import com.example.DAO.ActividadDAO;
import com.example.DAO.ClubDAO;
import com.example.DAO.InscripcionDAO;
import com.example.gui.LoginFrame;
import com.example.modelo.Actividad;
import com.example.modelo.Club;
import com.example.modelo.Inscripcion;



public class Main {
    public static final ArrayList<Club> listaClub = new ArrayList<>();
    public static final ArrayList<Actividad> listaActividad = new ArrayList<>();
    public static final ArrayList<Inscripcion> listaInscripcion = new ArrayList<>();
    
    public static void main(String[] args) {
        ClubDAO clubDAO = new ClubDAO();
        listaClub.addAll(clubDAO.obtenerClub());
        ActividadDAO actividadDAO = new ActividadDAO();
        listaActividad.addAll(actividadDAO.obtenerActividad());
        InscripcionDAO inscripcionDAO = new InscripcionDAO();
        listaInscripcion.addAll(inscripcionDAO.obtenerInscripcion());
        SwingUtilities.invokeLater(() -> {
            LoginFrame ventana = new LoginFrame();  
            ventana.setVisible(true);
        });   
        
        

    }
}