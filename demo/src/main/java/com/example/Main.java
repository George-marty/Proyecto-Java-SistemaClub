package com.example;


import java.util.ArrayList;

import javax.swing.SwingUtilities;

import com.example.DAO.ClubDAO;
import com.example.gui.LoginFrame;

import com.example.modelo.Actividad;
import com.example.modelo.Club;
import com.example.modelo.Inscripcion;
import com.example.modelo.Socio;



public class Main {
    public static final ArrayList<Club> listaClub = new ArrayList<>();
    public static final ArrayList<Actividad> listaActividad = new ArrayList<>();
    public static final ArrayList<Inscripcion> listaInscripcion = new ArrayList<>();
    public static final ArrayList<Socio> listaSocio = new ArrayList<>();
    public static void main(String[] args) {
        ClubDAO clubDAO = new ClubDAO();
        listaClub.addAll(clubDAO.obtenerClub());
        
        SwingUtilities.invokeLater(() -> {

            //MainFrame ventana = new MainFrame();
            LoginFrame ventana = new LoginFrame();  

            ventana.setVisible(true);
        });



         /*SwingUtilities.invokeLater(()-> {
            RegisterFrame ventana = new RegisterFrame();
            ventana.setVisible(true);
        }
    );*/
        
        
        
    

    
        
        
    }
}